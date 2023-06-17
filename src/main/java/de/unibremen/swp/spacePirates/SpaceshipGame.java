package de.unibremen.swp.spacePirates;

import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.FreeFlightCamera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;

/**
 * Hello world!
 *
 */
public class SpaceshipGame
{
    public void startGame( String[] args )
    {
        // set meta information about the game
        de.gurkenlabs.litiengine.Game.info().setName("GURK NUKEM");
        de.gurkenlabs.litiengine.Game.info().setSubTitle("");
        de.gurkenlabs.litiengine.Game.info().setVersion("v0.0.1");
        de.gurkenlabs.litiengine.Game.info().setWebsite("https://github.com/gurkenlabs/litiengine-gurk-nukem");
        de.gurkenlabs.litiengine.Game.info().setDescription("An example 2D platformer with shooter elements made in the LITIENGINE");

        // init the game infrastructure
        de.gurkenlabs.litiengine.Game.init(args);

        // set the icon for the game (this has to be done after initialization because the ScreenManager will not be present otherwise)
        //Game.window().setIcon(Resources.images().get("icon.png"));
        de.gurkenlabs.litiengine.Game.graphics().setBaseRenderScale(4f);

        // load data from the utiLITI game file
        Resources.load("res/config.litidata");

        // load the first level (resources for the map were implicitly loaded from the game file)
        de.gurkenlabs.litiengine.Game.world().loadEnvironment("mapv1");
        de.gurkenlabs.litiengine.Game.screens().add(new Rendering());

        Camera camera = new Camera();
        camera.setClampToMap(true);
        camera.setClampToMap(true);
        de.gurkenlabs.litiengine.Game.world().setCamera(camera);

        de.gurkenlabs.litiengine.Game.start();
    }
}
class Rendering extends GameScreen {
    public static final String NAME = "INGAME-SCREEN";
    public Rendering() {
        super(NAME);
    }
}

