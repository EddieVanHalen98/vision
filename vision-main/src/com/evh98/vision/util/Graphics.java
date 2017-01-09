/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.evh98.vision.Vision;

public class Graphics {
	
	public static FreeTypeFontGenerator font_roboto_thin;
	public static FreeTypeFontGenerator font_roboto_bold;
	
	public static OrthographicCamera camera;
	
	public static Sprite default_movie;
	
	public static ParticleEffect particles;
	
	private static GlyphLayout glyph_layout;

	/**
	 * Load all graphics
	 */
	public static void loadAll() {
		loadFonts();
		loadCamera();
        loadSprites();
		loadParticles();
		
		glyph_layout = new GlyphLayout();
	}
	
    /**
     * Internal font loading method
     */
	private static void loadFonts() {
		font_roboto_thin = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto-thin.ttf"));
		font_roboto_bold = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto-bold.ttf"));
	}
	
    /**
     * Internal camera loading method
     */
	private static void loadCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Vision.WIDTH, Vision.HEIGHT);
	}
	
    /**
     * Internal particles loading method
     */
	private static void loadParticles() {
		particles = new ParticleEffect();
		particles.load(Gdx.files.internal("particles/particles.p"), Gdx.files.internal("particles/"));
		particles.setPosition(Vision.ANCHOR[0], Vision.ANCHOR[1]);
		particles.scaleEffect(Vision.SCALE);
	}
    
    /**
     * Internal textures loading method
     */
    private static void loadSprites() {
    	Icons.loadAll();
    	
    	default_movie = createSprite(Gdx.files.internal("defaults/movie.png"));
    }
	
	/**
	 * Draws a sprite under Vision scaling relative to the global anchor point with a specified color
	 */
	public static void drawSprite(SpriteBatch sprite_batch, Sprite sprite, float x, float y, Color color) {
		Color original = sprite_batch.getColor();
		sprite_batch.setColor(color);
		sprite_batch.draw(sprite, Vision.ANCHOR[0] + (((x / Vision.HORIZONTAL_SCALE) - (sprite.getWidth() / 2)) * Vision.SCALE), Vision.ANCHOR[1] + ((y - (sprite.getHeight() / 2)) * Vision.SCALE), sprite.getWidth() * Vision.SCALE, sprite.getHeight() * Vision.SCALE);
		sprite_batch.setColor(original);
	}
	
	/**
	 * Draws a sprite with a specified size under Vision scaling relative to the global anchor point with a specified color
	 */
	public static void drawSprite(SpriteBatch sprite_batch, Sprite sprite, float x, float y, float width, float height, Color color) {
		Color original = sprite_batch.getColor();
		sprite_batch.setColor(color);
		sprite_batch.draw(sprite, Vision.ANCHOR[0] + ((x / Vision.HORIZONTAL_SCALE) * Vision.SCALE), Vision.ANCHOR[1] + (y * Vision.SCALE), width * Vision.SCALE, height * Vision.SCALE);
		sprite_batch.setColor(original);
	}
	
	/**
	 * Draws a sprite with a specified size under Vision scaling relative to the global anchor point
	 */
	public static void drawSprite(SpriteBatch sprite_batch, Sprite sprite, float x, float y, float width, float height) {
		sprite_batch.draw(sprite, Vision.ANCHOR[0] + ((x / Vision.HORIZONTAL_SCALE) * Vision.SCALE), Vision.ANCHOR[1] + (y * Vision.SCALE), (width / Vision.HORIZONTAL_SCALE) * Vision.SCALE, height * Vision.SCALE);
	}

	/**
	 * Draws a rectangle under Vision scaling relative to the global anchor point
	 */
	public static void drawRect(ShapeRenderer shape_renderer, float x, float y, float width, float height) {
		shape_renderer.rect(Vision.ANCHOR[0] + ((x / Vision.HORIZONTAL_SCALE) * Vision.SCALE), Vision.ANCHOR[1] + (y * Vision.SCALE), (width / Vision.HORIZONTAL_SCALE) * Vision.SCALE, height * Vision.SCALE);
	}

	/**
	 * Draws a circle under Vision scaling relative to the global anchor point
	 */
	public static void drawCircle(ShapeRenderer shape_renderer, float x, float y, float radius) {
		shape_renderer.circle(Vision.ANCHOR[0] + ((x / Vision.HORIZONTAL_SCALE) * Vision.SCALE), Vision.HEIGHT - (Vision.ANCHOR[1] + (y * Vision.SCALE)), radius * Vision.SCALE);
	}

	/**
	 * Draws text using relative to the global anchor point
	 */
	public static void drawText(SpriteBatch sprite_batch, BitmapFont font, String text, float x, float y) {
		glyph_layout.setText(font, text);
		float cx = x - ((glyph_layout.width / Vision.SCALE) / 2);
		float cy = y - ((glyph_layout.height / Vision.SCALE) / 2);
		font.draw(sprite_batch, glyph_layout, Vision.ANCHOR[0] + ((cx / Vision.HORIZONTAL_SCALE) * Vision.SCALE), Vision.ANCHOR[1] + (cy * Vision.SCALE));
	}
	
	/**
	 * Creates a BitmapFont from a FreeTypeFont with a specified size
	 */
	public static BitmapFont createFont(FreeTypeFontGenerator type, int size) {
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = (int) (size * Vision.SCALE);
		param.color = Palette.LIGHT_GRAY;
		param.flip = true;
		BitmapFont font = type.generateFont(param);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return font;
	}
    
    /**
     * Creates a sprite with custom properties
     */
    public static Sprite createSprite(FileHandle path) {
        Texture t = new Texture(path);
        t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        Sprite s = new Sprite(t);
        s.flip(false, true);
        return s;
    }
	
	/**
	 * Changes the emitter of the particles to the specified color
	 */
	public static void setParticles(Color color) {
		float[] colors = new float[3];
		
		if (color == Palette.WHITE) {
			particles.load(Gdx.files.internal("particles/particles.p"), Gdx.files.internal("particles/"));
			particles.setPosition(Vision.ANCHOR[0], Vision.ANCHOR[1]);
			particles.scaleEffect(Vision.SCALE);
		} else {
			colors[0] = color.r;
			colors[1] = color.g;
			colors[2] = color.b;
			
			for (int i = 0; i < 4; i++) {
				particles.getEmitters().get(i).getTint().setColors(colors);
			}
		}
	}
	
	/**
	 * Disposes all assets
	 */
	public static void dispose() {
		font_roboto_thin.dispose();
	}
}
