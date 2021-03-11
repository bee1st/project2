package kr.co.food.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.food.dao.IndexDao;
import kr.co.food.dao.NoticeDao;
import kr.co.food.dao.PriceDao;
import kr.co.food.dao.TrendDao;
import kr.co.food.dao.WeekDao;
import kr.co.food.dto.NoticeDto;
import kr.co.food.dto.PriceDto;
import kr.co.food.dto.TrendDto;
import kr.co.food.dto.WeekDto;

@Controller
public class IndexController {

	@Autowired
	public SqlSession sqlSession;

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	@RequestMapping("/sample")
	public String sampe() {
		return "/sample";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		/*Total현황*/
		IndexDao idao = sqlSession.getMapper(IndexDao.class);
		int food=idao.getFoodCnt();
		int user=idao.getUserCnt();
		int recipe=idao.getRecipeCnt();
		int trend=idao.getTrendCnt();
		int price=idao.getPriceCnt();
		
		model.addAttribute("food", food);
		model.addAttribute("user", user);
		model.addAttribute("recipe", recipe);
		model.addAttribute("trend", trend);
		model.addAttribute("price", price);
		/*공지사항*/
		NoticeDao ndao = sqlSession.getMapper(NoticeDao.class);
		ArrayList<NoticeDto> inlist = ndao.inlist();
		model.addAttribute("inlist", inlist);
		
		/*트렌드*/
		TrendDao dao = sqlSession.getMapper(TrendDao.class);
		ArrayList<TrendDto> tlist = dao.getList();
		model.addAttribute("tlist", tlist);
		
		/*주간식단*/
		WeekDao wdao = sqlSession.getMapper(WeekDao.class);
		for (int i=0; i<18; i++) {
			WeekDto meal = wdao.getMeal(i+1);
			String model_name = "meal"+(i+1);
			model.addAttribute(model_name.toString(), meal);
		}
		
		/*가격정보*/
		PriceDao pdao=sqlSession.getMapper(PriceDao.class);
		ArrayList<PriceDto> plist=pdao.getTop5();
		model.addAttribute("plist", plist);
		
		ArrayList foodlist=new ArrayList();
		for(int i=0;i<plist.size();i++) {
			String foodname=plist.get(i).getFood_name();
			foodlist.add(foodname);
		}
		//System.out.println(foodlist);
		ArrayList<PriceDto> food1=pdao.getPrice(foodlist.get(0).toString());
		model.addAttribute("food1",food1);
		ArrayList<PriceDto> food2=pdao.getPrice(foodlist.get(1).toString());
		model.addAttribute("food2",food2);
		ArrayList<PriceDto> food3=pdao.getPrice(foodlist.get(2).toString());
		model.addAttribute("food3",food3);
		ArrayList<PriceDto> food4=pdao.getPrice(foodlist.get(3).toString());
		model.addAttribute("food4",food4);
		ArrayList<PriceDto> food5=pdao.getPrice(foodlist.get(4).toString());
		model.addAttribute("food5",food5);

		return "/index";

	}
}