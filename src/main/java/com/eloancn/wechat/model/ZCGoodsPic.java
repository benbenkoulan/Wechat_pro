package com.eloancn.wechat.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 
 * ClassName: ZCGoodsPic <br/>
 * Function:众筹产品图片 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-10-9 上午10:02:00 <br/>
 *
 * @author liuzhen
 * @version
 */
@Alias("zCGoodsPic")
public class ZCGoodsPic implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String  pic;//图片路径
	private Integer gid;//产品id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
}

