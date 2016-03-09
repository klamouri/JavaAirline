<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="contentPlein">
		<!--Contenu ICI-->
		<h1>Planning</h1>
		<p>Bonjour, selectionner le vol que vous voulez supprimer</p>
		<c:choose>
		
		<c:when test="${empty requestScope.listeDepart}">
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
   				<th>Action</th>
			</tr>
			<c:forEach items="${requestScope.listeDepart}" var="depart">
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
                		<a href="<c:url value="/SupprimerVol"/>?numVol=<c:out value="${depart.numvol.numvol}"/>" class="suppr"></a>
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