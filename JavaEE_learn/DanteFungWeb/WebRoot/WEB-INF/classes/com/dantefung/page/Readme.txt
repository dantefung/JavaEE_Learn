


try {
			if(curPage == null )
			{
				curPage = 1;
			}
			
			if(pageSize == null)
			{
				pageSize = 10;
			}	
			
			List<Dept> depts = deptService.findByPage((curPage-1)*pageSize, pageSize);
			int count = deptService.getTotalCount().intValue();
			
			// 封装
			PageBean pageBean = new PageBean();
			//【用户】
			pageBean.setCurPage(curPage);
			pageBean.setPageSize(pageSize);
			// 【数据库】
			pageBean.setData(depts);
			pageBean.setTotalCount(count);
			ActionContext.getContext().put("pageBean", pageBean);
			return "list";
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			ActionContext.getContext().put("msg", "程序猿正在极力修复服务器。。。");
			return "input";
		}




--------------------------------------------------------------------------            
     <tr class="page">
        <td align="right" colspan="9">
        <%--
        	<s:a href=""></s:a>  href 不用写  "/"
        	值类型标签：写上%{ }
        --%>
        <s:if test="%{#pageBean.curPage == 1}">
		              首页
					&nbsp;
		              上一页
		
		 </s:if>
          <s:else>
		          <s:a href="dept_list.action?curPage=%{#pageBean.firstPage}" class="like_btn">
		            首页
		          </s:a>
		          <s:a href="dept_list.action?curPage=%{#pageBean.prePage}" class="like_btn">
		            上一页
		          </s:a>
          </s:else>
          
           <s:if test="%{#pageBean.curPage == #pageBean.totalPage}">
		             下一页
					&nbsp;
		            尾页
		          </s:if>
           <s:else>
		          <s:a href="dept_list.action?curPage=%{#pageBean.nextPage}" class="like_btn">
		            下一页
		          </s:a>
		          <s:a href="dept_list.action?curPage=%{#pageBean.totalPage}" class="like_btn">
		            尾页
		          </s:a>
           </s:else>
          共<s:property value="#pageBean.totalCount"/>条纪录，当前第<s:property value="#pageBean.curPage"></s:property>/<s:property value="#pageBean.totalPage"></s:property>页，每页<s:property value="#pageBean.pageSize"></s:property>条纪录 </td>
      </tr>