package com.pboproject.game.states;

import com.pboproject.game.GamePanel;
import com.pboproject.game.entity.Chest;
import com.pboproject.game.entity.Door;
import com.pboproject.game.entity.Enemy;
import com.pboproject.game.entity.Player;
import com.pboproject.game.graphics.Font;
import com.pboproject.game.graphics.Sprite;
import com.pboproject.game.tiles.TileManager;
import com.pboproject.game.util.KeyHandler;
import com.pboproject.game.util.MouseHandler;
import com.pboproject.game.util.Vector2f;

import java.awt.*;
import java.sql.*;

public class PlayState extends GameState {

	private Font font;
	private Player player;
	private Enemy enemy, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9,enemy10, enemy11, enemy12, enemy13, enemy14;
	private Enemy enemy15, enemy16;
	private TileManager tm;
	private Connection connection;

	private Chest chest1, chest2, chest3;
	private Door door;

	private String highestLevel;

	public static Vector2f map;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		dbOpen();
		//resetDb(); //reset highest level ever reached to 1
		ResultSet rs = select();
		try {
			while(rs.next()) {
				highestLevel = rs.getString("highest_level_recorded");
			}
		}catch (Exception e){

		}
		map = new Vector2f(-181, 161);
		Vector2f.setWorldVar(map.x, map.y);

		tm = new TileManager("tile/tilemap.xml");
		font = new Font("font/font.png", 10, 10);

		enemy = new Enemy(new Sprite("entity/orc spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 300, 0 + (GamePanel.height / 2) - 32 + 600), 50);
		enemy2 = new Enemy(new Sprite("entity/orc spritesheet calciumtrice2.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 600, 0 + (GamePanel.height / 2) - 32 + 700), 50);
		enemy3 = new Enemy(new Sprite("entity/skeleton spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 700, 0 + (GamePanel.height / 2) - 32 + 500), 50, 500);
		enemy4 = new Enemy(new Sprite("entity/skeleton spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 1665, 0 + (GamePanel.height / 2) - 32 + 1170), 50,200);
		enemy5 = new Enemy(new Sprite("entity/orc spritesheet calciumtrice2.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 1463, 0 + (GamePanel.height / 2) - 32 + 1325), 50, 200);
		enemy6 = new Enemy(new Sprite("entity/wizardPlayer3.png", 60, 61), new Vector2f(0 + (GamePanel.width / 2) - 32 + 1316, 0 + (GamePanel.height / 2) - 32 + 154), 50,300);
		enemy7 = new Enemy(new Sprite("entity/littlegirl.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 749, 0 + (GamePanel.height / 2) - 32 + 1128), 50, 350);
		enemy8 = new Enemy(new Sprite("entity/littlegirl.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 217, 0 + (GamePanel.height / 2) - 32 + 1723), 50, 350);
		enemy9 = new Enemy(new Sprite("entity/orc spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + -249, 0 + (GamePanel.height / 2) - 32 + 2427), 50, 230);
		enemy10 = new Enemy(new Sprite("entity/orc spritesheet calciumtrice2.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 981, 0 + (GamePanel.height / 2) - 32 + 1794), 50, 250);
		enemy11 = new Enemy(new Sprite("entity/skeleton spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 843, 0 + (GamePanel.height / 2) - 32 + 2561), 50, 250);
		enemy12 = new Enemy(new Sprite("entity/skeleton spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 1710, 0 + (GamePanel.height / 2) - 32 + 1679), 50, 290);
		enemy13 = new Enemy(new Sprite("entity/wizardPlayer3.png", 60, 61), new Vector2f(0 + (GamePanel.width / 2) - 32 + 2261, 0 + (GamePanel.height / 2) - 32 + 1831), 50, 290);
		enemy14 = new Enemy(new Sprite("entity/orc spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 2228, 0 + (GamePanel.height / 2) - 32 + 2486), 50, 150);
		enemy15 = new Enemy(new Sprite("entity/skeleton spritesheet calciumtrice.png", 32, 32), new Vector2f(0 + (GamePanel.width / 2) - 32 + 2059, 0 + (GamePanel.height / 2) - 32 + 2375), 50, 150);
		enemy16 = new Enemy(new Sprite("entity/wizardPlayer3.png", 60, 61), new Vector2f(0 + (GamePanel.width / 2) - 32 + 1009, 0 + (GamePanel.height / 2) - 32 + 910), 50, 400);
		enemy.setAcc(3f);
		enemy.setMaxSpeed(4f);
		enemy2.setAcc(3f);
		enemy2.setMaxSpeed(2f);
		enemy3.setAcc(4f);
		enemy3.setMaxSpeed(4f);
		enemy4.setAcc(2f);
		enemy5.setAcc(2f);
		enemy9.setAcc(3.2f);
		enemy4.setMaxSpeed(2f);
		enemy5.setMaxSpeed(2f);
		enemy9.setMaxSpeed(3.2f);

		//enemy10.setAcc(2f);
		enemy10.setAcc(4f);
		enemy11.setAcc(4f);
		enemy14.setAcc(4f);
		enemy15.setAcc(4f);
		enemy10.setMaxSpeed(3.2f);
		enemy11.setMaxSpeed(3.2f);
		enemy14.setMaxSpeed(2f);
		enemy15.setMaxSpeed(2f);
		door = new Door(new Sprite("entity/ClosedDoor.png", 85, 130), new Vector2f(0 + (GamePanel.width / 2) - 32 + 1685, 0 + (GamePanel.height / 2) - 32 + 1385), 55);
		chest1 = new Chest(new Sprite("entity/chest.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + -161, 0 + (GamePanel.height / 2) - 32 + 2487), 64, 120);
		chest2 = new Chest(new Sprite("entity/chest.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 928, 0 + (GamePanel.height / 2) - 32 + 2506), 64, 120);
		chest3 = new Chest(new Sprite("entity/chest.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 2145, 0 + (GamePanel.height / 2) - 32 + 2481), 64, 120);
		player = new Player(new Sprite("entity/linkFormatted2.png"), new Vector2f(-181+ (GamePanel.width / 2) - 32, 161 + (GamePanel.height / 2) - 32), 64);

		//player = new Player(new Sprite("entity/linkFormatted2.png"), new Vector2f(-171+ (GamePanel.width / 2) - 32, 2174 + (GamePanel.height / 2) - 32), 64);
	}

	public void dbOpen(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_dungeonescape", "root", "");
			if(connection==null){
				System.err.println("Koneksi database gagal");
			}else{
				System.out.println("Koneksi database berhasil");
			}
		}catch (Exception ex){
			System.err.println("Driver Error");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public void updateSQL(String level){
		try{
			String sql = "UPDATE level_record SET highest_level_recorded ='"+level+"' WHERE id_record="+1;
			PreparedStatement stat = connection.prepareStatement(sql);
			int rowsUpdate = stat.executeUpdate();
//            if(rowsUpdate>0){
//                System.out.println("Berhasil Updata data");
//            }else{
//                System.out.println("Tidak berhasil Updata data");
//            }
		}catch (Exception e){
			System.err.println("Update Gagal");
			e.printStackTrace();
		}

	}

	public void resetDb(){
		updateSQL("1");
	}

	public ResultSet select(){
		ResultSet rs = null;
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM level_record");
		}catch (Exception e){
			System.out.println("Tidak berhasil select");
			e.printStackTrace();
		}
		return rs;
	}

	public void update() {
		Vector2f.setWorldVar(map.x, map.y);
		player.update(enemy, chest1, chest2, chest3, door);
		door.update(player);
		enemy.update(player);
		enemy2.update(player);
		enemy3.update(player);
		enemy4.update(player);
		enemy5.update(player);
		enemy6.update(player);
		enemy7.update(player);
		enemy8.update(player);
		enemy9.update(player);
		enemy10.update(player);
		enemy11.update(player);
		enemy12.update(player);
		enemy13.update(player);
		enemy14.update(player);
		enemy15.update(player);
		enemy16.update(player);
		chest1.update(player);
		chest2.update(player);
		chest3.update(player);

		if(player.getLevel()>Integer.valueOf(highestLevel)){
			updateSQL(String.valueOf(player.getLevel()));
			highestLevel = String.valueOf(player.getLevel());
		}
		//System.out.println(((player.getPos().x)-608)+" dan "+((player.getPos().y)-328));
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}

	public void render(Graphics2D g) {
		tm.render(g);
		String fps = GamePanel.oldFrameCount + " FPS";
		Sprite.drawArray(g, font, fps, new Vector2f(GamePanel.width - fps.length() * 32, 32), 32, 24);

		String key = String.valueOf(player.getKeyObtain()+"/3 Key Obtained");
		Sprite.drawArray(g, key, new Vector2f(GamePanel.width - key.length() * 50, 64), 32, 25);

		String lvl = String.valueOf("Level "+player.getLevel());
		Sprite.drawArray(g, lvl, new Vector2f(GamePanel.width - key.length() * 78, 64), 40, 35);

		String highest_lvl = String.valueOf("Highest Level: "+highestLevel);
		Sprite.drawArray(g, highest_lvl, new Vector2f(GamePanel.width - key.length() * 78, 25), 25, 15);
		
		player.render(g);
		enemy.render(g);
		enemy2.render(g);
		enemy3.render(g);
		enemy4.render(g);
		enemy5.render(g);
		enemy6.render(g);
		enemy7.render(g);
		enemy8.render(g);
		enemy9.render(g);
		enemy10.render(g);
		enemy11.render(g);
		enemy12.render(g);
		enemy13.render(g);
		enemy14.render(g);
		enemy15.render(g);
		enemy16.render(g);
		chest1.render(g);
		chest2.render(g);
		chest3.render(g);
		door.render(g);
	}
}
