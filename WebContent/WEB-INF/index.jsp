<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="abs4.utils.HtmlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="_header.jsp"/>
<jsp:include page="_success.jsp"/>
<jsp:include page="_error.jsp"/>

	<div class="row pt-4">
			<div class="col">
				<nav class="float-left">
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href="?now=${now.minusYears(1)}"><span class="oi oi-chevron-left"></span><span class="oi oi-chevron-left"></span> 前年</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="?now=${now.minusMonths(1)}"><span class="oi oi-chevron-left"></span> 前月</a>
						</li>
					</ul>
				</nav>
			</div>

			<div class="col text-center">
				<h2 class="font-weight-bold"><span class="oi oi-calendar"></span> ${now.year}年${now.monthValue}月</h2>
			</div>

			<div class="col">
				<nav class="float-right">
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href="?now=${now.plusMonths(1)}">翌月 <span class="oi oi-chevron-right"></span></a>
						</li>
						<li class="page-item">
							<a class="page-link" href="?now=${now.plusYears(1)}">翌年 <span class="oi oi-chevron-right"></span><span class="oi oi-chevron-right"></span></a>
						</li>
					</ul>
				</nav>
			</div>
		</div>

		<div class="row pt-1">
			<div class="col">
				<div class="card bg-light border-info mb-4">
					<div class="card-header text-center"><span class="oi oi-yen"></span> 今月の収入合計 <small class="text-muted">（先月比）</small></div>
					<div class="card-body">
						<p class="card-text text-center">${HtmlUtils.formatCost(thisMonthIncome)} <small class="${thisMonthIncomeDiff < 0 ? 'text-danger' : 'text-info'}">（${HtmlUtils.formatCostDiff(thisMonthIncomeDiff)}）</small></p>
					</div>
				</div>
			</div>

			<div class="col">
				<div class="card bg-light border-dark mb-4">
					<div class="card-header text-center"><span class="oi oi-yen"></span> 今月の支出合計 <small class="text-muted">（先月比）</small></div>
					<div class="card-body">
						<p class="card-text text-center">${HtmlUtils.formatCost(thisMonthOutgo)} <small class="${thisMonthOutgoDiff < 0 ? 'text-danger' : 'text-info'}">（${HtmlUtils.formatCostDiff(thisMonthOutgoDiff)}）</small></p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" style="width: 90px;">#</th>
							<th scope="col" style="width: 120px;">日付</th>
							<th scope="col">カテゴリー</th>
							<th scope="col">備考</th>
							<th scope="col" style="width: 120px;">金額</th>
						</tr>
					</thead>

					<tbody>
						<c:if test="${list.size() > 0 }">
							<c:forEach var="detail" items="${list}">
							<tr class="${detail.cost > 0 ? 'table-info' : 'table-light' }">
								<th scope="row">
									<div class="btn-group">
										<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											操作
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="detail.html?id=${detail.id}"><span class="oi oi-spreadsheet"></span> 詳細</a>
											<a class="dropdown-item" href="copy.html?id=${detail.id}"><span class="oi oi-paperclip"></span> コピー</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item delete-btn" href="detail.html?id=${detail.id}"><span class="oi oi-trash"></span> 削除</a>
										</div>
									</div>
								</th>

								<td>${HtmlUtils.formatDay(detail)}</td>
								<td>${detail.type}</td>
								<td>${detail.content}</td>
								<td class="text-right">${HtmlUtils.formatCost(detail)}</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${list.size() eq 0 }">
							<tr class="table-light">
								<td colspan="5">データが1件もありません。</td>
							</tr>
						</c:if>

					</tbody>
				</table>
			</div>
		</div>

<jsp:include page="_footer.jsp"/>