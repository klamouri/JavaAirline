<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
     		<div id="corps">
     			<div id="info">
     				<h1>Info</h1><br/>
     				C formulaire permet :
     				<ol>
     					<li>De s'inscrire comme pilote</li>
     					<li>De s'inscrire comme passager</li>
     				</ol>
     			</div>
     			<div id="content">
     				<!--Contenu ICI-->
     				<form method="post" action="<c:url value="/MotDePasse"/>">
     					<fieldset>
     						<legend>Inscription</legend>
     						<hr/>
     						
  							
     						
     						<div class="elemForm">
     						<label for="mdpOld">Ancien Mot de Passe :</label>
     						<input type="password" name="mdpOld" id="mdpOld"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="mdp">Nouveau Mot de Passe :</label>
     						<input type="password" name="mdp" id="mdp"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="mdp2">Confirmation :</label>
     						<input type="password" name="mdp2" id="mdp2"/>
     						</div>
     						
     						
     					</fieldset>
     					<input type="submit" value="Envoyer" class="btndroite" />
     				</form>
     			</div>
     		</div>
<c:import url="/inc/foot.jsp" />