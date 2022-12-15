package com.pboproject.game.entity;


import com.pboproject.game.states.PlayState;
import com.pboproject.game.GamePanel;
import com.pboproject.game.graphics.Sprite;
import com.pboproject.game.util.KeyHandler;
import com.pboproject.game.util.MouseHandler;
import com.pboproject.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {
    protected int keyObtain = 0;
    protected int level = 1;

    public Player(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);
        fallen = false;
        acc = 6f;
        maxSpeed = 7f;
        deacc = 1f;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
    }

    private void move() {
        if(up) {
            dy -= acc;
            if(dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if(dy < 0) {
                dy += deacc;
                if(dy > 0) {
                    dy = 0;
                }
            }
        }
        if(down) {
            dy += acc;
            if(dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if(dy > 0) {
                dy -= deacc;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }
        if(left) {
            dx -= acc;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if(dx < 0) {
                dx += deacc;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }
        if(right) {
            dx += acc;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if(dx > 0) {
                dx -= deacc;
                if(dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void setLevel(int i){level = i;}
    public int getLevel(){return level;}
    public void resetPosition() {
        System.out.println("Reseting Player... ");
        pos.x = -181+GamePanel.width / 2 - 32;
        PlayState.map.x = -181;
        pos.y = 161+GamePanel.height /2 - 32;
        PlayState.map.y = 161;
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);

    }

    public void setObtainKey(int i){keyObtain = i;}
    public int getKeyObtain(){return  keyObtain;}

    public void update(Enemy enemy, Chest ch1, Chest ch2, Chest ch3, Door door) {
        super.update();

        if(attack && hitBounds.collides(enemy.getBounds())) {
            System.out.println("I've been hit!");
        }

        if(finished){
            if(ani.hasPlayedOnce()) {
                dx = 0;
                dy = 0;
                ch1.reset();
                ch2.reset();
                ch3.reset();
                door.reset();
                setObtainKey(0);
                resetPosition();
                finished = false;
            }
        }

        if(!fallen) {
            move();
            if(!tc.collisionTile(dx, 0)) {
                PlayState.map.x += dx;
                pos.x += dx;
            }
            if(!tc.collisionTile(0, dy)) {
                PlayState.map.y += dy;
                pos.y += dy;
            }
        } else {
            if(ani.hasPlayedOnce()) {
                resetPosition();
                dx = 0;
                dy = 0;
                fallen = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
//        g.setColor(Color.green);
//        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

//        d

        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if(mouse.getButton() == 1) {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }

        if(!fallen) {
            if(key.up.down) {
                up = true;
            } else {
                up = false;
            }
            if(key.down.down) {
                down = true;
            } else {
                down = false;
            }
            if(key.left.down) {
                left = true;
            } else {
                left = false;
            }
            if(key.right.down) {
                right = true;
            } else {
                right = false;
            }
            if(key.attack.down) {
                attack = true;
            } else {
                attack = false;
            }
        } else {
            up = false;
            down = false;
            right = false;
            left = false;
        }
    }
}
