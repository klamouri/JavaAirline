<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="contentPlein">
		<!--Contenu ICI-->
		<h1>Planning</h1>
		<p>Bonjour <c:out value="${sessionScope.utilisateur.nom}"/>, vous �tes un  <c:out value="${sessionScope.utilisateur.typeSpecialisation}"/> et voici votre planning</p>
		<p>Note : L'attente en mod�ration signifie que vous avez souhait� vous d�saffecter d'un vol, mais qu'un administrateur doit le valider<br/>
		Si vous vous �tes trompez, recliquez sur l'action, et la demande de desafectation disparaitra (Cela est possible tant que l'Admin ne vous a pas d�saffect� du vol)
		</p>
		<c:choose>
		
		<c:when test="${empty sessionScope.utilisateur.departs}">
			<h6>D�sol�, il n'y a pas de d�parts pr�vus</h6>
		</c:when>
		
		<c:otherwise>
		<table class="tableauliste">
       		<tr>
       			
        		 <th>Num�ro de Vol</th>
        		 <th>Avion</th>
            	<th>Date</th>
            	<th>Heure de D�part</th>
       			<th>Heure d'Arriv�e</th>
       			<th>Ville de D�part</th>
       			<th>Ville d'Arriv�e</th>
       			<th>En attente de mod�ration ?</th>
   				<th>Action</th>
			</tr>
			<c:forEach items="${sessionScope.utilisateur.departs}" var="depart">
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
   					<c:if test="${depart.moderation}">Oui</c:if>
   					<c:if test="${!depart.moderation}">Non</c:if>
   				</td>
               	<td>
               		<span class="liens">
                		<a href="<c:url value="/SupprimerDepart"/>?numVol=<c:out value="${depart.numvol.numvol}"/>&jDate=<joda:format value="${depart.dateDepart}" pattern="dd"/>&mDate=<joda:format value="${depart.dateDepart}" pattern="MM"/>&aDate=<joda:format value="${depart.dateDepart}" pattern="yyyy"/>" class="suppr"></a>
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