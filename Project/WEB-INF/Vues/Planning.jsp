<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="contentPlein">
		<!--Contenu ICI-->
		<h1>Planning</h1>
		<p>Bonjour <c:out value="${sessionScope.utilisateur.nom}"/>, vous êtes un  <c:out value="${sessionScope.utilisateur.typeSpecialisation}"/> et voici votre planning</p>
		<table class="tableauliste">
       		<tr>
       			
        		<th>id</th>
            	<th>Nom</th>
            	<th>Prénom</th>
       			<th>Mail</th>
   				<th>Action</th>
			</tr>
   			<tr>
   				<td>1</td>
       					<td>Ilié</td>
               			<td>Jean-Michel</td>
               			<td>jimmy@ping.fr</td>
               			<td>
               				<span class="liens">
                   				<a href="detail.html" class="detail"></a>
                					<a href="#" class="suppr"></a>
       			    				<a href="ajout.html" class="modif"></a>
                    			</span>
                			</td> 
           				</tr>
            			<tr>
            				<td>2</td>
                			<td>Rincon</td>
                			<td>Philippe</td>
                			<td>phil@ping.fr</td>
                			<td>
                				<span class="liens">
                    				<a href="detail.html" class="detail"></a>
                					<a href="#" class="suppr"></a>
       			    				<a href="ajout.html" class="modif"></a>
                    			</span>
                			</td> 
            			</tr>
						<tr>
           					<td>3</td>
                			<td>Guillaume</td>
                			<td>Daniel</td>
                			<td>dg@ping.fr</td>
                			<td>
                				<span class="liens">
                    				<a href="detail.html" class="detail"></a>
                					<a href="#" class="suppr"></a>
       			    				<a href="ajout.html" class="modif"></a>
                    			</span>
                			</td>  
            			</tr>
            			<tr>
            				<td>4</td>
               				<td>Froussard</td>
                			<td>Amédé</td>
                			<td>amede@ping.fr</td>
                			<td>
                				<span class="liens">
                    				<a href="detail.html" class="detail"></a>
                					<a href="#" class="suppr"></a>
       			    				<a href="ajout.html" class="modif"></a>
                    			</span>
                			</td> 
            			</tr>
            			<tr>
            				<td class="col1">5</td>
                			<td>Pabo</td>
                			<td>Hugo</td>
                			<td>hugo@ping.fr</td>
                			<td>
                				<span class="liens">
                    				<a href="detail.html" class="detail"></a>
                					<a href="#" class="suppr"></a>
       			    				<a href="ajout.html" class="modif"></a>
                    			</span>
                			</td>  
            			</tr>
        			</table>
					<div id="numerotation">     
						<ul id="pagination">
    						<li class="paginationprecdesact">&lt; Préc</li>
    						<li class="paginationactive">1</li>
    						<li><a href="#">2</a></li>
    						<li><a href="#">3</a></li>
    						<li><a href="#">4</a></li>
    						<li class="paginationsuiv"><a href="#">Suiv &gt;</a></li>
						</ul>
					</div>
     			</div>
     		</div>
<c:import url="/inc/foot.jsp" />