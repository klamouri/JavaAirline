<c:import url="/inc/head.jsp" />
<c:import url="/inc/menu.jsp" />
			<div id="corps">
				<div id="info">
     				<h1>Info</h1><br/>
     				Ce formulaire permet :
     				<ol>
     					<li>De modifier le pilote pour un vol</li>
     				</ol>
     			</div>
     			<div id="content">
     				<!--Contenu ICI-->
     				<form method="post" action="<c:url value="/Moderation"/>">
     					<fieldset>
     						<legend>Affecter un nouveau Pilote au vol</legend>
     						<hr/>
     						<div class="elemForm">
     						<label>Numéro de Vol :</label>
							<span><c:out value="${requestScope.numvol }"/></span>			
     						</div>
     						
     						<div class="elemForm">
     							<label for="pilote">Pilote :</label>
     							<select name="pilote" id="pilote">
     								<c:forEach var="pilote" items="${requestScope.listePilote }">
     									<option value="<c:out value="${pilote.login }"/>"><c:out value="${pilote.nom }"/></option>
     								</c:forEach>
     							</select>
     						</div>
     						<input type = "hidden" name="numvol" value="<c:out value="${requestScope.numvol }"/>">
     						<input type = "hidden" name="jDate" value="<c:out value="${requestScope.jDate }"/>">
     						<input type = "hidden" name="mDate" value="<c:out value="${requestScope.mDate }"/>">
     						<input type = "hidden" name="aDate" value="<c:out value="${requestScope.aDate }"/>">
     					</fieldset>
     					<input type="submit" value="Envoyer" class="btndroite" />
     				</form>
     			</div>
     		</div>

<c:import url="/inc/foot.jsp" />
     	