package com.pboproject.game.entity;

import com.pboproject.game.graphics.Sprite;
import com.pboproject.game.util.AABB;
import com.pboproject.game.util.Vector2f;

import java.awt.*;

public class Chest extends Entity{

    private AABB sense;
    private boolean opened;

    public Chest(Sprite img, Vector2f orgin, int size, int r){
        super(img, orgin, size);
        opened = false;
        r = r;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

        sense = new AABB(new Vector2f(orgin.x + size / 2 - r / 2, orgin.y + size / 2 - r / 2), r);
    }

    public void status(Player player) {
        if(sense.colCircleBox(player.getBounds())) {
            if (!(pos.y - 20 > player.pos.y + 20) && !(pos.x - 20 > player.pos.x + 20) && !(pos.y + 20 < player.pos.y - 20) && !(pos.x + 20 < player.pos.x - 20)) {
                setSprite(new Sprite("entity/chestUnlocked2.png", 48, 48));
                if(opened == false){
                    player.keyObtain++;
                    opened = true;
                }
            }
        }
    }

    public void setEntitySize(int i){this.size = i;}

    public void reset(){
        setSprite(new Sprite("entity/chest.png", 48, 48));
        opened = false;
    }

    public void update(Player player) {
        super.update();
        status(player);
        if(!fallen) {
            if(!tc.collisionTile(dx, 0)) {
                sense.getPos().x  += dx;
                pos.x += dx;
            }
            if(!tc.collisionTile(0, dy)) {
                sense.getPos().y  += dy;
                pos.y += dy;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }
}
