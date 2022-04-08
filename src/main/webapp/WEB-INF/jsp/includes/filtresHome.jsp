 <form class="filterArticle" action="rechercher" method="post">
        <div class="group-achats">
            <label for="achats">Mes achats
                <input type="radio" id="achats" name="filterConnected" value="achats" checked="checked"/>
            </label>
            <div class="group-checks">
                <label for="e-ouverte">
                    <input type="checkbox" id="e-ouverte" name="e-ouverte" />enchères ouvertes
                </label>
                <label for="e-enCours">
                    <input type="checkbox" id="e-enCours" name="e-enCours" />mes enchères en cours
                </label>
                <label for="e-remportees">
                    <input type="checkbox" id="e-remportees" name="e-remportees" />mes enchères remportées
                </label>
            </div>
        </div>

        <div class="group-ventes">
            <label for="ventes">Mes ventes
                <input type="radio" id="ventes" name="filterConnected" value="ventes" />
            </label>
            <div class="group-checks">
                <label for="v-enCours">
                    <input type="checkbox" id="v-enCours" name="v-enCours" disabled="disabled" />Mes ventes en cours
                </label>
                <label for="v-nonDeb">
                    <input type="checkbox" id="v-nonDeb" name="v-nonDeb" disabled="disabled" />
                    Ventes non débutées
                </label>
                <label for="v-termine">
                    <input type="checkbox" id="v-termine" name="v-termine" disabled="disabled" />mes enchères remportées
                </label>
            </div>
        </div>

        <div class="btnsForm">
            <button type ="submit">Rechercher</button>
        </div>

    </form>