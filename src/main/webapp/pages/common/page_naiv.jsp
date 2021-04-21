<%--
  Created by IntelliJ IDEA.
  User: 曾昕阳
  Date: 2021/3/21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div id="page_nav">
    <%--大于首页才显示 首页和上一页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}?pageNo=1">首页</a>
        <a href="${requestScope.page.url}?pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--页码输出开始 5 个--%>
    <%--情况一 不足5页 输出1-总页码--%>
    <%--				${requestScope.page.pageTotal}--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <%--当前页不可点击--%>
                <c:if test="${requestScope.page.pageNo == i}">
                    【${requestScope.page.pageNo}】
                </c:if>
                <c:if test="${requestScope.page.pageNo != i}">
                    <a href="${requestScope.page.url}?pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        
        <%--情况二 总页码大于5页 --%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <%--1.页码小于4 输出1到5页--%>
            <c:choose>
                <c:when test="${requestScope.page.pageNo < 4}">
                    <c:forEach begin="1" end="5" var="i">
                        <%--当前页不可点击--%>
                        <c:if test="${requestScope.page.pageNo == i}">
                            【${requestScope.page.pageNo}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != i}">
                            <a href="${requestScope.page.url}?pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--2.页码大于total-3 输出total-4到total页--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <%--当前页不可点击--%>
                        <c:if test="${requestScope.page.pageNo == i}">
                            【${requestScope.page.pageNo}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != i}">
                            <a href="${requestScope.page.url}?pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--3.其他情况 输出i-2到i+2页-%>--%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <%--当前页不可点击--%>
                        <c:if test="${requestScope.page.pageNo == i}">
                            【${requestScope.page.pageNo}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo != i}">
                            <a href="${requestScope.page.url}?pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--			【${requestScope.page.pageNo}】
    <%--			<a href="#">5</a>--%>
    <%--页码输出结束--%>
    <%--如果是最后一页 不显示末页和下一页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}?pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}?pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="searchPageBtn">
</div>
</html>
