package net.orbitalchainsaw.openryuubox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import net.orbitalchainsaw.openryuubox.OpenRyuuBox;

import static com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;

/**
 * Created by Jean-Vincent on 13/10/2014.
 */
public class MenuScreen implements Screen{
    final OpenRyuuBox game;

    private String joueur;
    private Texture background;
    private OrthographicCamera camera;
    private Stage stage = new Stage();
    private Table table = new Table();
    private BitmapFont font1 = new BitmapFont();


    private Skin skin = new Skin(Gdx.files.internal("mainscreenui/uiskin.json"));
    private TextButton Options = new TextButton("Regles du Jeu", skin);


    /* Creation des textures du personnage 1 */
    private Texture j1 = new Texture (Gdx.files.internal("boxes/lapin.png"));
    private Texture j1Click = new Texture(Gdx.files.internal("boxes/lapinClick.png"));
    private Texture j1Checked = new Texture(Gdx.files.internal("boxes/lapinChecked.png"));

    /* Personnalisation du bouton pour avoir l'image du personnage */
    TextButtonStyle joueur1 = new TextButtonStyle (
            new TextureRegionDrawable(new TextureRegion(j1)),
            new TextureRegionDrawable(new TextureRegion (j1Click)),
            new TextureRegionDrawable(new TextureRegion(j1Checked)), font1);



    /* Creation des différentes textures du personnage 2 */
    private Texture j2 = new Texture (Gdx.files.internal("boxes/panda.png"));
    private Texture j2Click = new Texture(Gdx.files.internal("boxes/pandaclick.png"));
    private Texture j2Checked = new Texture(Gdx.files.internal("boxes/pandachecked.png"));

    /* Personnalisation du bouton pour avoir l'image du personnage */
    TextButtonStyle joueur2 = new TextButtonStyle (
            new TextureRegionDrawable(new TextureRegion(j2)),
            new TextureRegionDrawable(new TextureRegion (j2Click)),
            new TextureRegionDrawable(new TextureRegion(j2Checked)), font1);



    /* Creation des différentes textures du personnage 3 */
    private Texture j3 = new Texture (Gdx.files.internal("boxes/chat.png"));
    private Texture j3Click = new Texture(Gdx.files.internal("boxes/chatclick.png"));
    private Texture j3Checked = new Texture(Gdx.files.internal("boxes/chatchecked.png"));

    /* Personnalisation du bouton pour avoir l'image du personnage */
    TextButtonStyle joueur3 = new TextButtonStyle (
            new TextureRegionDrawable(new TextureRegion(j3)),
            new TextureRegionDrawable(new TextureRegion (j3Click)),
            new TextureRegionDrawable(new TextureRegion(j3Checked)), font1);



    private TextButton perso1 = new TextButton("Lapin ", joueur1);
    private TextButton perso2 = new TextButton("Panda" , joueur2);
    private TextButton perso3 = new TextButton("Chat", joueur3);



    public MenuScreen(final OpenRyuuBox game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Options.setPosition(600, 245);
        perso1.setPosition(50, 325);
        perso2.setPosition(50, 180);
        perso3.setPosition(50, 50);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

        table.add(Options).size(100,60).padBottom(10).row();
        table.add(perso1).size(128,128).padBottom(10).row();
        table.add(perso2).size(128,128).padBottom(10).row();
        table.add(perso3).size(128,128).padBottom(10).row();

        background = new Texture(Gdx.files.internal("boxes/fondDebut.jpg"));

        Options.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
                dispose();
            }
        });

        perso1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                joueur = "lapin";
                game.setScreen(new LevelSelectionScreen(game, joueur));
                dispose();
            }
        });

        perso2.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                joueur = "panda";
                game.setScreen(new LevelSelectionScreen(game,joueur));
                dispose();
            }
        });

        perso3.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                joueur = "chat";
                game.setScreen(new LevelSelectionScreen(game,joueur));
                dispose();
            }
        });


        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
