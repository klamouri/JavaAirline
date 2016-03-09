<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="contentPlein">
		<!--Contenu ICI-->
		<h1>Planning</h1>
		<p>Bonjour <c:out value="${sessionScope.utilisateur.nom}"/>, vous êtes un  <c:out value="${sessionScope.utilisateur.typeSpecialisation}"/> et voici les départs à modérer</p>
		<c:choose>
		
		<c:when test="${empty sessionScope.utilisateur.moderations}">
			<h6>Désolé, vous n'avez rien à modérer</h6>
		</c:when>
		
		<c:otherwise>
		<table class="tableauliste">
       		<tr>
       			
        		<th>Numéro de Vol</th>
        		<th>Avion</th>
            	<th>Date</th>
            	<th>Heure de Départ</th>
       			<th>Heure d'Arrivée</th>
       			<th>Ville de Départ</th>
       			<th>Ville d'Arrivée</th>
       			<th>Pilote</th>
       			<th>Action</th>
			</tr>
			
			<c:forEach items="${sessionScope.utilisateur.moderations}" var="depart">
   			<tr>
   				<td>
   					<c:out value="${depart.numvol.numvol }"/>
   				</td>
   				<td>
   					<c:out value="${depart.numav.type }"/>
   				</td>
   				<td>
   					<joda:format value="${depart.dateDepart}" pattern="dd/MM/yyyy"/>
   				</td>
   				<td>
   					<joda:format value="${depart.numvol.heureDepart}" pattern="HH:mm"/>
   				</td>
   				<td>
   					<joda:format value="${depart.numvol.heureArrivee}" pattern="HH:mm"/>
   				</td>
   				<td>
   					<c:out value="${depart.numvol.villeDepart}"/>
   				</td>
   				<td>
   					<c:out value="${depart.numvol.villeArrivee}"/>
   				</td>
   				<td>
   					<c:out value="${depart.matricule.login}"/>
   				</td>
   				<td><span class="liens"><a href="<c:url value="Moderation"/>?numvol=<c:out value="${depart.numvol.numvol}"/>&jDate=<joda:format value="${depart.dateDepart}" pattern="dd"/>&mDate=<joda:format value="${depart.dateDepart}" pattern="MM"/>&aDate=<joda:format value="${depart.dateDepart}" pattern="yyyy"/>" class="detail"></a></span></td>
           	</tr>
           	</c:forEach>
            
            				
        			</table>
        			</c:otherwise>
        			</c:choose>
     			</div>
     		</div>
<c:import url="/inc/foot.jsp" />