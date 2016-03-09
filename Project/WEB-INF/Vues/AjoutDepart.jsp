<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
     		<div id="corps">
     			<div id="info">
     				<h1>Info</h1><br/>
     				Ce formulaire permet :
     				<ol>
     					<li>De creer un départ pour que des passager puissent s'y inscrire</li>
     				</ol>
     			</div>
     			<div id="content">
     				<!--Contenu ICI-->
     				<form method="post" action="<c:url value="/AjoutDepart"/>">
     					<fieldset>
     						<legend>Inscription</legend>
     						<hr/>
     						
  							<div class="elemForm">
     						<label for="numvol">Numéro du vol :</label>
     						<input type="text" name="numvol" id="numvol"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="jDate">Date de départ :</label>
							<input type="text" name="jDate" id="jDate" size="2" maxlength="2"/> /
							<input type="text" name="mDate" id="mDate"  size="2" maxlength="2"/> /
							<input type="text" name="aDate" id="aDate"  size="4" maxlength="4"/>
							</div>
     						
     						
     						<div class="elemForm">
     						<label for="hDateDep">Heure de départ :</label>
							<input type="text" name="hDateDep" id="hDateDep" size="2" maxlength="2"/> :
							<input type="text" name="MinDateDep" id="MinDateDep"  size="2" maxlength="2"/>
							</div>
							
							<div class="elemForm">
     						<label for="hDateArr">Heure d'arrivé :</label>
							<input type="text" name="hDateArr" id="hDateArr" size="2" maxlength="2"/> :
							<input type="text" name="MinDateArr" id="MinDateArr"  size="2" maxlength="2"/>
							</div>
     						
     						
     						<div class="elemForm">
     						<label for="villeDep">Ville de départ :</label>
     						<input type="text" name="villeDep" id="villeDep"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="villeArr">Ville d'arrivé :</label>
     						<input type="text" name="villeArr" id="villeArr"/>
     						</div>
     						
     						<div class="elemForm">
     							<label for="login">Pilote :</label>
     							<select name="login" id="login">
     								<c:forEach var="pilote" items="${requestScope.listePilote }">
     									<option value="<c:out value="${pilote.login }"/>"><c:out value="${pilote.nom }"/></option>
     								</c:forEach>
     							</select>
     						</div>
     						
     						<div class="elemForm">
     							<label for="avion">Avion :</label>
     							<select name="avion" id="avion">
     								<c:forEach var="avion" items="${requestScope.listeAvion }">
     									<option value="<c:out value="${avion.numav }"/>"><c:out value="${avion.numav }"/> - <c:out value="${avion.type }"/></option>
     								</c:forEach>
     							</select>
     						</div>
     						
     					</fieldset>
     					<input type="submit" value="Envoyer" class="btndroite" />
     				</form>
     			</div>
     		</div>
<c:import url="/inc/foot.jsp" />