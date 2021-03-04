package kr.co.food.dao;

import java.util.ArrayList;

import kr.co.food.dto.NoticeDto;

public interface NoticeDao {
	public void write_ok(NoticeDto ndto);
	public ArrayList<NoticeDto> list(int index);
	public Integer get_record_cnt();//총레코드의 갯수
	public Integer get_page_cnt();//총페이지의 갯수
	public NoticeDto content(String notice_id);
	public void readnum(String notice_id);
	public NoticeDto update(String notice_id);
	public void update_ok(NoticeDto ndto);
	public void delete(String notice_id);
	public ArrayList<NoticeDto> slist(String sear,String sword,int index);//검색부분

}
