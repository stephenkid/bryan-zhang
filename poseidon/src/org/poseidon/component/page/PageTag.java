package org.poseidon.component.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


public class PageTag extends TagSupport{

    private static final long serialVersionUID = 1L;

    private String url;
    
    private Page page;
    
    @Override
    public int doStartTag() throws JspException {
        if (StringUtils.isEmpty(url)) {
            try {
                // 如果url为空则在页面中输出红色字体"url is empty!"
                pageContext.getOut().write("<font color='red'>url is empty!</font>");
            } catch (IOException e) {
                e.printStackTrace();
                return SKIP_PAGE;
            }
        }
        page = (Page) pageContext.getRequest().getAttribute("page");
        return EVAL_PAGE;
    }
    
    @Override
    public int doEndTag() throws JspException {
        StringBuffer sb = new StringBuffer();
        sb.append("<script language=\"javascript\">");
        sb.append("function chgPage(url,pageNum){");
        sb.append("     $('form').eq(0).attr('action',url+'?page.pageNum='+pageNum);");
        sb.append("     $('form').eq(0).submit();");
        sb.append("}");
        sb.append("</script>");
        
        if(page.getPageNum() == 0)
        {
            sb.append("首页");
        }
        else
        {
            sb.append("&nbsp;&nbsp;<a href=\"javascript:chgPage('" + url + "',1)\" >首页</a>");
        }
        
        if (page.hasPrePage()) {
            sb.append("&nbsp;&nbsp;<a href=\"javascript:chgPage('" + url + "'," + page.getPageNum() + ")\" >上一页</a>");
        }

        if (page.hasNextPage()) {
            sb.append("&nbsp;&nbsp;<a href=\"javascript:chgPage('" + url + "'," + (page.getPageNum() + 2) + ")\" >下一页</a>");
        }

        
        if(page.getPageNum()+1 == page.getTotalPage())
        {
            sb.append("末页"); 
        }
        else
        {
            sb.append("&nbsp;&nbsp;<a href=\"javascript:chgPage('" + url + "'," + page.getTotalPage() + ")\" >末页</a>");
        }

        if (page.getTotalRow() <= 0) {
            sb.append("&nbsp;&nbsp;第0页");
        } else {
            sb.append("&nbsp;&nbsp;第" + (page.getPageNum() + 1) + "页");
        }

        sb.append("&nbsp;&nbsp; 转至<input id='toPage' type='text' size='2' value=''>页"); 
        sb.append("&nbsp;&nbsp;<a href=\"javascript:chgPage('" + url + "',$('#toPage').val());\">Go</a>");
        sb.append("&nbsp;&nbsp;共" + page.getTotalPage() + "页"); 
        sb.append("&nbsp;&nbsp;" + page.getTotalRow() + "条记录");
        
        if(page.getTotalRow() > 0) {
            try {
                //System.out.println("-------------" + sb + "----------------");
                this.pageContext.getOut().write(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return EVAL_PAGE;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
