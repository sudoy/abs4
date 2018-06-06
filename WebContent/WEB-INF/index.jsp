<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>
<jsp:include page="success.jsp"/>
<jsp:include page="error.jsp"/>
<jsp:include page="current_data.jsp"/>



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
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/30</td>
							<td>日用品</td>
							<td>ティッシュペーパー、歯磨き粉など</td>
							<td class="text-right">-740</td>
						</tr>
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/30</td>
							<td>食費</td>
							<td>ランチ</td>
							<td class="text-right">-800</td>
						</tr>
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/30</td>
							<td>交際費</td>
							<td></td>
							<td class="text-right">-6,800</td>
						</tr>
						<tr class="table-info">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/31</td>
							<td>アルバイト代</td>
							<td></td>
							<td class="text-right">120,000</td>
						</tr>
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="update.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>

							</th>
							<td>2018/05/31</td>
							<td>交際費</td>
							<td></td>
							<td class="text-right">-6,800</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp"/>