/**
 * @Title: RespNewsModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */

package com.eloancn.code.core.model.resp;

import java.util.List;

/**
 * @ClassName: RespNewsModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:11:07
 */

public class RespNewsModel extends RespBaseModel {
    /**
     * 图文消息个数，限制为10条以内
     */
    private int articleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<RespArticleModel> articles;
    /**
     * @return articleCount
     */
    public int getArticleCount() {
        return articleCount;
    }
    /**
     * @param articleCount 要设置的 articleCount
     */
    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }
    /**
     * @return articles
     */
    public List<RespArticleModel> getArticles() {
        return articles;
    }
    /**
     * @param articles 要设置的 articles
     */
    public void setArticles(List<RespArticleModel> articles) {
        this.articles = articles;
    }
    
}
