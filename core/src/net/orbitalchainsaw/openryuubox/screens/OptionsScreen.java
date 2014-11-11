package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.orbitalchainsaw.openryuubox.GamePreferences;
import net.orbitalchainsaw.openryuubox.OpenRyuuBox;

/**
 * Created by Romain on 05/11/2014.
 */
public class OptionsScreen implements Screen {
    final OpenRyuuBox game;
    private Texture background;
    private OrthographicCamera camera;
    private Skin skinLibgdx = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
    private CheckBox chkSound = new CheckBox("Son", skinLibgdx);

    private TextButton quit = new TextButton("Quitter", skinLibgdx);
    private Stage stage = new Stage();
    private Table table = new Table();

    private Label label1 = new Label("C est quoi ?\n" + "\n" +
            "OPEN RYUU BOX est inspire d une méthode norvégienne pour s initier a la resolution d equations mathematiques. \n", skinLibgdx);

    private Label label2 = new Label ("Tous les détails\n" + "\n" +
            "Open Ryuu Box est compose d une seule difficulte. \n" +
            "Le but (et c est la seule chose qu il faut toujours vraiment garder a l esprit), c est d isoler la boite d un cote de l ecran. \n" +
            "Ce jeu permet egalement de creer ses propre niveaux.\n" +
            "L ecran est divise en deux cotes. " +
            "D un cote se trouve une boite. Des deux cotes se trouvent differentes cartes.\n " +
            "Pour reussir un niveau, il faut isoler la boite d un cote, et simplifier au maximum les cartes se trouvant de l autre cote.\n" +
            " On retrouve bien le principe de la resolution d equation du premier degre a une inconnue : la boite c est x, l’inconnue.\n " +
            "Il faut bien, pour resoudre une equation, isoler x d un cote du signe =, pour trouver combien vaut x.\n" +
            "Et il faut bien simplifier au maximum l expression de cette equivalence.\n ", skinLibgdx);

    public OptionsScreen(final OpenRyuuBox game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void saveSettings(){
        GamePreferences prefs = GamePreferences.instance;
        prefs.sound = chkSound.isChecked();
    }

    private void loadSettings(){
        GamePreferences prefs = GamePreferences.instance;
        prefs.load();
        chkSound.setChecked(prefs.sound);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        label1.setPosition(400, 400);
        label1.setColor(Color.BLACK);
        label1.setFontScale(0.8f);


        label2.setPosition(400, 400);
        label2.setColor(Color.BLACK);
        label2.setFontScale(0.8f);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//corrige sans caméra !!
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {

    }

    public void show() {
        table.add(label1).row();
        table.add(label2).row();

        table.add(quit).size(100,60).padBottom(10).row();
        background = new Texture(Gdx.files.internal("boxes/REGLES.png"));

        quit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    public void hide() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
