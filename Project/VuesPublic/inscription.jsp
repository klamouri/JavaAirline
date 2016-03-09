<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
     		<div id="corps">
     			<div id="info">
     				<h1>Info</h1><br/>
     				Ce formulaire permet :
     				<ol>
     					<li>De s'inscrire comme pilote</li>
     					<li>De s'inscrire comme passager</li>
     				</ol>
     			</div>
     			<div id="content">
     				<!--Contenu ICI-->
     				<form method="post" action="<c:url value="/Inscription"/>">
     					<fieldset>
     						<legend>Inscription</legend>
     						<hr/>
     						
  							<div class="elemForm">
     						<label for="login">Login :</label>
     						<input type="text" name="login" id="login"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="mdp">Mot de Passe :</label>
     						<input type="password" name="mdp" id="mdp"/>
     						</div>
     						
     						
     						<div class="elemForm">
     						<label for="nom">Nom :</label>
     						<input type="text" name="nom" id="nom"/>
     						</div>
     						

     						<div class="elemForm">
     						<label for="ville">Ville (Pilote) :</label>
     						<input type="text" name="ville" id="ville"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="age">Age (Pilote) :</label>
     						<input type="text" name="age" id="age"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="salaire">Salaire (Pilote) :</label>
     						<input type="text" name="salaire" id="salaire"/>
     						</div>
     						
     						<div class="elemForm">
     						<label for="pilote">Pilote ? :</label>
     						<input type="checkbox" name="pilote" id="pilote"/>
     						</div>
     						
     						
     					</fieldset>
     					<input type="submit" value="Envoyer" class="btndroite" />
     				</form>
     			</div>
     		</div>
<c:import url="/inc/foot.jsp" />