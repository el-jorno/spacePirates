package de.unibremen.swp.spacePirates;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.FreeFlightCamera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
class Player extends Creature implements IUpdateable {
    private static Player instance;

    public static Player instance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }

    private Player() {
        super("ship");
    }

    @Override
    public void update() {
    }

    @Override
    protected IMovementController createMovementController() {
        // setup movement controller
        return new PlatformingMovementController<>(this);
    }
}

public class Controller {
    Game g;
    MapScreen mapScreen;
    MenueScreen menueScreen;

    Controller(String[] args ){
        //Initialisierung, was muss beim Start passieren
        createGame(args);
    }

    public static void main(String[] args){
        Controller c = new Controller(args);
    }

    public void createGame(String[] args){
        de.gurkenlabs.litiengine.Game.info().setName("Spaceship");
        de.gurkenlabs.litiengine.Game.info().setVersion("v0.0.1");
        de.gurkenlabs.litiengine.Game.info().setDescription("SWP Projekt von thisTimeForSure");
        de.gurkenlabs.litiengine.Game.init(args);
        de.gurkenlabs.litiengine.Game.window().setResolution(Resolution.Ratio16x9.RES_1280x720);

        de.gurkenlabs.litiengine.Game.graphics().setBaseRenderScale(1.5f);
        //Map.litidata
        //Resources.load("res/config.litidata");
        Resources.load("res/gamev2.litidata");

        // load the first level (resources for the map were implicitly loaded from the game file)
        MenueScreen menueScreen = new MenueScreen();
        MapScreen mapScreen = new MapScreen();

        de.gurkenlabs.litiengine.Game.world().loadEnvironment("mapv7");
        //de.gurkenlabs.litiengine.Game.world().loadEnvironment("mapmenue");

        de.gurkenlabs.litiengine.Game.screens().add(mapScreen);
        de.gurkenlabs.litiengine.Game.screens().add(menueScreen);
        de.gurkenlabs.litiengine.Game.start();
        loadMap();

        Camera camera = new PositionLockCamera(Player.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);

        // set a basic gravity for all levels.
        Game.world().setGravity(5);

        // add default game logic for when a level was loaded
        Game.world().onLoaded(e -> {
            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = e.getSpawnpoint("spawnship");
            if (enter != null) {
                enter.spawn(Player.instance());
            }
        });
    }

    public void loadMap(){
        de.gurkenlabs.litiengine.Game.screens().display("map");
    }
    public void loadMenue(){
        de.gurkenlabs.litiengine.Game.screens().display("menue");
    }

    public void loadFight(){
        //TODO
    }

    public class MenueScreen extends GameScreen {
        public static final String NAME = "menue";
        public MenueScreen() {
            super(NAME);
        }
        @Override
        public void render(final Graphics2D g) {
            super.render(g);
            g.setFont(Resources.fonts().get("customfont.ttf",32f));
            g.setColor(Color.RED);
            TextRenderer.render(g, "Test text", 100, 100);
        }
    }

    class MapScreen extends GameScreen {
        public static final String NAME = "map";
        public MapScreen() {
            super(NAME);
        }
        @Override
        public void render(final Graphics2D g) {
            super.render(g);
            g.setColor(Color.RED);
            //TextRenderer.render(g, "Test text", 100, 100);
        }
    }
}
