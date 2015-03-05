<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag body-content="empty" %>
<%@ attribute name="page" required="true" type="com.excilys.cdb.view.Page"%>

<div class="container text-center">
	<ul class="pagination">
		<c:if test="${page.page > 1}">
			<li>
				<a href="Dashboard?p=${page.page - 1}&search=${page.search}&nb=${page.nbElementPage}" aria-label="Previous" >
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
		</c:if>

		<c:forEach var="i" begin="0" end="${page.nbChoice - 1}">
			<c:if test="${(page.page - (page.nbChoice-i)) > 0}">
				<li>
					<a href="Dashboard?p=${page.page - (page.nbChoice-i)}&search=${page.search}&nb=${page.nbElementPage}" >
						${page.page - (page.nbChoice-i)}
					</a>
				</li>
			</c:if>
		</c:forEach>
		
		<li>
			<a href="Dashboard?p=${page.page}&search=${page.search}&nb=${page.nbElementPage}" >
				<b>${page.page}</b>
			</a>
		</li>
		
		<c:forEach var="i" begin="1" end="${page.nbChoice}">
			<c:if test="${(page.page + i) <= page.nbpage}">
				<li>
					<a href="Dashboard?p=${page.page + i}&search=${page.search}&nb=${page.nbElementPage}" >
						${page.page + i}
					</a>
				</li>
			</c:if>
		</c:forEach>
			
		<c:if test="${page.page < page.nbpage}">
			<li>
				<a href="Dashboard?p=${page.page + 1}&search=${page.search}&nb=${page.nbElementPage}"  aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</c:if>
	</ul>
	
	<div class="btn-group btn-group-sm pull-right" role="group">
	<form action="Dashboard" method="post">
	<input type="text" value="${page.search}" name="search" hidden="true" />
	<button name="nbB" type="submit" value="10" class="btn btn-default" >10</button>
	<button name="nbB" type="submit" value="50" class="btn btn-default" >50</button>
	<button name="nbB" type="submit" value="100" class="btn btn-default" >100</button>
	</form></div></div>