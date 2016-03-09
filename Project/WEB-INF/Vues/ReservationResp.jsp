<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="contentPlein">
		<!--Contenu ICI-->
		<h1>Reserver</h1>
		<p>Bonjour <c:out value="${sessionScope.utilisateur.nom}"/>, r�server un vol correspondant � vos crit�res</p>
		
		<c:choose>
		
		<c:when test="${empty sessionScope.liste}">
			<h6>D�sol�, il n'y a pas de r�servation possible correspondant � vos crit�res</h6>
		</c:when>
		
		<c:otherwise>
			
			<table class="tableauliste">
       		<tr>
       			
        		<th>Num�ro de Vol</th>
            	<th>Date</th>
            	<th>Heure de D�part</th>
       			<th>Heure d'Arriv�e</th>
       			<th>Ville de D�part</th>
       			<th>Ville d'Arriv�e</th>
   				<th>Reserver Sans Assurance</th>
   				<th>Reserver Avec Assurance</th>
			</tr>
			<c:forEach items="${sessionScope.liste}" var="depart">
   			<tr>
   				<td>
   					<c:out value="${depart.numvol.numvol }"/>
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
               		<span class="liens">
                		<a href="<c:url value="/AjoutReservation"/>?numVol=<c:out value="${depart.numvol.numvol}"/>&jDate=<joda:format value="${depart.dateDepart}" pattern="dd"/>&mDate=<joda:format value="${depart.dateDepart}" pattern="MM"/>&aDate=<joda:format value="${depart.dateDepart}" pattern="yyyy"/>&assurance=false" class="modif"></a>
                   	</span>
                </td>
                <td>
               		<span class="liens">
                		<a href="<c:url value="/AjoutReservation"/>?numVol=<c:out value="${depart.numvol.numvol}"/>&jDate=<joda:format value="${depart.dateDepart}" pattern="dd"/>&mDate=<joda:format value="${depart.dateDepart}" pattern="MM"/>&aDate=<joda:format value="${depart.dateDepart}" pattern="yyyy"/>&assurance=true" class="modif"></a>
                   	</span>
                </td>
           	</tr>
           	</c:forEach>
           	</table>
				
		</c:otherwise>
		</c:choose>
		
     </div>
</div>
<c:import url="/inc/foot.jsp" />