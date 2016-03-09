<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="contentPlein">
		<!--Contenu ICI-->
		<h1>Planning</h1>
		<p>Bonjour <c:out value="${sessionScope.utilisateur.nom}"/>, vous �tes un  <c:out value="${sessionScope.utilisateur.typeSpecialisation}"/> et voici votre planning</p>
		<c:choose>
		
		<c:when test="${empty sessionScope.utilisateur.reservations}">
			<h6>D�sol�, vous n'avez pas de r�servation en cours</h6>
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
       			<th>Assur�</th>
   				<th>Action</th>
			</tr>
			<c:forEach items="${sessionScope.utilisateur.reservations}" var="reservation">
   			<tr>
   				<td>
   					<c:out value="${reservation.vol.numvol }"/>
   				</td>
   				<td>
   					<joda:format value="${reservation.dateDepart}" pattern="dd/MM/yyyy"/>
   				</td>
   				<td>
   					<joda:format value="${reservation.vol.heureDepart}" pattern="HH:mm"/>
   				</td>
   				<td>
   					<joda:format value="${reservation.vol.heureArrivee}" pattern="HH:mm"/>
   				</td>
   				<td>
   					<c:out value="${reservation.vol.villeDepart}"/>
   				</td>
   				<td>
   					<c:out value="${reservation.vol.villeArrivee}"/>
   				</td>
   				<td>
   					<c:if test="${reservation.assurance}">Oui</c:if>
   					<c:if test="${!reservation.assurance}">Non</c:if>
   				</td>
               	<td>
               		<span class="liens">
                		<a href="<c:url value="/SupprimerReservation"/>?numVol=<c:out value="${reservation.vol.numvol}"/>&jDate=<joda:format value="${reservation.dateDepart}" pattern="dd"/>&mDate=<joda:format value="${reservation.dateDepart}" pattern="MM"/>&aDate=<joda:format value="${reservation.dateDepart}" pattern="yyyy"/>" class="suppr"></a>
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