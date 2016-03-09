<header>
	<div id="HeaderPremierBloc"></div>
	<nav>
		<ul>
			<li><a href="<c:url value="/"/>">ACCUEIL</a></li>
			<!--<c:out value="${sessionScope.utilisateur}" />-->
			<c:choose>
				<c:when test="${sessionScope.utilisateur!=null}">
				
					<c:forEach items="${sessionScope.utilisateur.services}" var="services">
						<c:forEach items="${services}" var="serv" varStatus="status">
							<c:choose>
								<c:when test="${status.count==1}">
									<li><a href="<c:url value="${serv}" />">
								</c:when>
								<c:otherwise>
									<c:out value="${serv}"/> </a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:forEach>
					
					
					
					
				</c:when>
				<c:otherwise>
					<li>
					<a href="<c:url value="/Inscription"/>">INSCRIPTION</a>
					</li>
				</c:otherwise>
			</c:choose>



		</ul>
	</nav>
	<div id="HeaderTroisiemeBloc"></div>
</header>