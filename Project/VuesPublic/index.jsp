<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
<div id="corps">
	<div id="info">
		<h1>Notes</h1>
		<br /> Cette page permet l'utilisateur de s'identifier pour accèder à 
		l'application. Seul les utilisateurs "administrateur" peuvent accèder au
		service.
	</div>
	<div id="content">
		<!--Contenu ICI-->
		<form
			method="post"
			action="<c:url value="/Connexion"/>"
		>
			<fieldset>
				<legend>Merci de bien vouloir vous identifier</legend>
				<hr />
				<div class="elemForm">
					<label for="identifiant">Identifiant :</label> <input
						type="text"
						name="identifiant"
						id="identifiant"
					/>
				</div>

				<div class="elemForm">
					<label for="mdp">Mot de passe :</label> <input
						type="password"
						name="mdp"
						id="mdp"
					/>
				</div>

			</fieldset>
			<input
				type="submit"
				value="Envoyer"
				class="btndroite"
			/>
		</form>
	</div>
</div>
<c:import url="/inc/foot.jsp" />