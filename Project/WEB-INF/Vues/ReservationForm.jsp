<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
     		<div id="corps">
     			<div id="info">
     				<h1>Info</h1><br/>
     				Ce formulaire permet :
     				<ol>
     					<li>De r�server un vol</li>
     				</ol>
     			</div>
     			<div id="content">
     				<!--Contenu ICI-->
     				<form method="post" action="<c:url value="/Reservation"/>">
     					<fieldset>
     						<legend>R�servation :</legend>
     						<hr/>
     						
  							<div class="elemForm">
     						<label for="login">Ville de D�part :</label>
     						<input type="text" name="villeDep" id="villeDep"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="mdp">Ville d'Arriv�e :</label>
     						<input type="text" name="villeArr" id="villeArr"/>
     						</div>
     						
     						
     						<div class="elemForm">
     						<label for="jDate">Date de d�part :</label>
							<input type="text" name="jDate" id="jDate" size="2" maxlength="2"/>/
							<input type="text" name="mDate" id="mDate"  size="2" maxlength="2"/>/
							<input type="text" name="aDate" id="aDate"  size="4" maxlength="4"/>
							</div>
     						
     					</fieldset>
     					<input type="submit" value="Envoyer" class="btndroite" />
     				</form>
     			</div>
     		</div>
<c:import url="/inc/foot.jsp" />