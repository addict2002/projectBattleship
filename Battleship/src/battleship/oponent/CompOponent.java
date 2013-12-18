/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship.oponent;

import battleship.engine.*;
import battleship.engine.actions.*;
import battleship.grid.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Andri
 */
public class CompOponent implements IOponent {

    Player oponent;
    Bomb lastBombOnTarget;
    int bombingDirection = 0; //0: no direction, 1: horizontal, 2: vertical

    public CompOponent() {
        oponent = new Player();
    }

    @Override
    public void init() {
        if(placeShips()){
            readyFromOponent();
        }else{
            oponent=new Player();
            Engine.getEngine().pushAction(new EngineAction(99,"Computer couldn't place ships"));
        }
    }

    public boolean placeShips() {
        int placedShips = 0;
        int counter = 0;
        while (placedShips < oponent.ships.length && counter < 50000) {
            if (placeShipOnRandomField(oponent.ships[placedShips])) {
                placedShips++;
            }
            counter++;
        }
        return true;
    }

    private boolean placeShipOnRandomField(Ship aship) {
        Random generator = new Random();
        int x = generator.nextInt(oponent.grid.grid.length);
        int y = generator.nextInt(oponent.grid.grid.length);
        return oponent.placeShip(x, y, generator.nextBoolean(), aship.shipLength);
    }

    private Bomb getNextBomb() {
        Random generator = new Random();
        ArrayList<Bomb> bombs = getBestPossibleBombs();
        if (bombs.size() > 0) {
            return bombs.get(generator.nextInt(bombs.size()));
        } else {
            return null;
        }
    }

    private ArrayList<Bomb> getBestPossibleBombs() {
        ArrayList<Bomb> bombs = new ArrayList<>();
        if (lastBombOnTarget != null) {

            int xmin = lastBombOnTarget.x - 1;
            int xmax = lastBombOnTarget.x + 1;
            int ymin = lastBombOnTarget.y - 1;
            int ymax = lastBombOnTarget.y + 1;
            boolean checkNext = true;
            switch (bombingDirection) {
                case 1:
                    while (checkNext) {
                        checkNext = false;
                        GridField afield = oponent.shootingGrid.getField(xmin, lastBombOnTarget.y);
                        if (afield != null) {
                            if (afield.hasShip) {
                                checkNext = true;
                                xmin--;
                            } else if (!afield.bombed) {
                                bombs.add(new Bomb(xmin, lastBombOnTarget.y));
                            }
                        }
                        afield = oponent.shootingGrid.getField(xmax, lastBombOnTarget.y);
                        if (afield != null) {
                            if (afield.hasShip) {
                                checkNext = true;
                                xmax++;
                            } else if (!afield.bombed) {
                                bombs.add(new Bomb(xmax, lastBombOnTarget.y));
                            }
                        }
                    }
                    return bombs;
                case 2:
                    while (checkNext) {
                        checkNext = false;
                        GridField afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymin);
                        if (afield != null) {
                            if (afield.hasShip) {
                                checkNext = true;
                                ymin--;
                            } else if (!afield.bombed) {
                                bombs.add(new Bomb(lastBombOnTarget.x, ymin));
                            }
                        }
                        afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymax);
                        if (afield != null) {
                            if (afield.hasShip) {
                                checkNext = true;
                                ymax++;
                            } else if (!afield.bombed) {
                                bombs.add(new Bomb(lastBombOnTarget.x, ymax));
                            }
                        }
                    }
                    return bombs;
                case 0:
                default:
                    GridField afield = oponent.shootingGrid.getField(xmin, lastBombOnTarget.y);
                    if (afield != null) {
                        if (afield.hasShip) {
                            bombingDirection = 1;
                            return getBestPossibleBombs();
                        } else if (!afield.bombed) {
                            bombs.add(new Bomb(xmin, lastBombOnTarget.y));
                        }
                    }
                    afield = oponent.shootingGrid.getField(xmax, lastBombOnTarget.y);
                    if (afield != null) {
                        if (afield.hasShip) {
                            bombingDirection = 1;
                            return getBestPossibleBombs();
                        } else if (!afield.bombed) {
                            bombs.add(new Bomb(xmax, lastBombOnTarget.y));
                        }
                    }
                    afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymin);
                    if (afield != null) {
                        if (afield.hasShip) {
                            bombingDirection = 2;
                            return getBestPossibleBombs();
                        } else if (!afield.bombed) {
                            bombs.add(new Bomb(lastBombOnTarget.x, ymin));
                        }
                    }
                    afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymax);
                    if (afield != null) {
                        if (afield.hasShip) {
                            bombingDirection = 2;
                            return getBestPossibleBombs();
                        } else if (!afield.bombed) {
                            bombs.add(new Bomb(lastBombOnTarget.x, ymax));
                        }
                    }
            }

            return bombs;
        } else {
            for (int y = 0; y < oponent.shootingGrid.grid.length; y++) {
                for (int x = 0; x < oponent.shootingGrid.grid[0].length; x++) {
                    if (!oponent.shootingGrid.getField(x, y).bombed) {
                        bombs.add(new Bomb(x, y));
                    }
                }
            }
        }
        return bombs;
    }

    private void markDestroyedShip() {
        if (lastBombOnTarget != null) {

            int xmin = lastBombOnTarget.x;
            int xmax = lastBombOnTarget.x + 1;
            int ymin = lastBombOnTarget.y;
            int ymax = lastBombOnTarget.y + 1;
            boolean checkNext = true;
            switch (bombingDirection) {
                case 1:
                    while (checkNext) {
                        checkNext = false;
                        GridField afield = oponent.shootingGrid.getField(xmin, lastBombOnTarget.y);
                        if (afield != null) {
                            afield.bombed = true;

                            GridField otherField = oponent.shootingGrid.getField(xmin, lastBombOnTarget.y - 1);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }
                            otherField = oponent.shootingGrid.getField(xmin, lastBombOnTarget.y + 1);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }

                            if (afield.hasShip) {
                                checkNext = true;
                                xmin--;
                            }

                        }
                        afield = oponent.shootingGrid.getField(xmax, lastBombOnTarget.y);
                        if (afield != null) {
                            afield.bombed = true;
                            GridField otherField = oponent.shootingGrid.getField(xmax, lastBombOnTarget.y - 1);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }
                            otherField = oponent.shootingGrid.getField(xmax, lastBombOnTarget.y + 1);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }

                            if (afield.hasShip && afield.bombed) {
                                checkNext = true;
                                xmax++;
                            }
                        }
                    }
                    break;
                case 2:
                    while (checkNext) {
                        checkNext = false;
                        GridField afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymin);
                        if (afield != null) {
                            afield.bombed = true;

                            GridField otherField = oponent.shootingGrid.getField(lastBombOnTarget.x - 1, ymin);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }
                            otherField = oponent.shootingGrid.getField(lastBombOnTarget.x + 1, ymin);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }

                            if (afield.hasShip) {
                                checkNext = true;
                                ymin--;
                            }

                        }
                        afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymax);
                        if (afield != null) {
                            afield.bombed = true;
                            GridField otherField = oponent.shootingGrid.getField(lastBombOnTarget.x - 1, ymax);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }
                            otherField = oponent.shootingGrid.getField(lastBombOnTarget.x + 1, ymax);
                            if (otherField != null) {
                                otherField.bombed = true;
                            }

                            if (afield.hasShip) {
                                checkNext = true;
                                ymax++;
                            }

                        }
                    }
                    break;
                case 0:
                default:

                    xmin--;
                    ymin--;
                    GridField afield = oponent.shootingGrid.getField(xmin, lastBombOnTarget.y);
                    if (afield != null) {
                        afield.bombed = true;
                        if (afield.hasShip) {
                            bombingDirection = 1;
                            markDestroyedShip();
                            return;
                        }
                    }
                    afield = oponent.shootingGrid.getField(xmax, lastBombOnTarget.y);
                    if (afield != null) {
                        afield.bombed = true;
                        if (afield.hasShip) {
                            bombingDirection = 1;
                            markDestroyedShip();
                            return;
                        }
                    }
                    //vertical
                    afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymin);
                    if (afield != null) {
                        afield.bombed = true;
                        if (afield.hasShip) {
                            bombingDirection = 2;
                            markDestroyedShip();
                            return;
                        }
                    }
                    afield = oponent.shootingGrid.getField(lastBombOnTarget.x, ymax);
                    if (afield != null) {
                        afield.bombed = true;
                        if (afield.hasShip) {
                            bombingDirection = 2;
                            markDestroyedShip();
                            return;
                        }
                    }
                    for(int y=ymin;y<=ymax;y++){
                        for(int x=xmin;x<=xmax;x++){
                            afield=oponent.shootingGrid.getField(x, y);
                            if(afield!=null){
                                afield.bombed=true;
                            }
                        }
                    }
                    break;
            }
        }
        lastBombOnTarget = null;
    }

    @Override
    public boolean sendBombToOponent(Bomb aBomb) {
        if(!oponent.ready){
            return false;
        }
        BombReport report = oponent.receiveBomb(aBomb);
        bombReportFromOponent(report);
        if (oponent.yourTurn) {
            bombFromOponent(getNextBomb());
        }
        return true;
    }

    @Override
    public boolean sendBombReportToOponent(BombReport aReport) {
        if(!oponent.ready){
            return false;
        }
        if (aReport.bombOnShip) {

            lastBombOnTarget = aReport.bomb;
            oponent.shootingGrid.getField(lastBombOnTarget.x, lastBombOnTarget.y).hasShip = true;
            if (aReport.shipDestroyed) {
                markDestroyedShip();
            }
            bombFromOponent(getNextBomb());
        }
        return true;
    }

    @Override
    public void bombFromOponent(Bomb aBomb) {
        oponent.sendBombShootingGrid(aBomb);

        Engine engine = Engine.getEngine();
        engine.pushAction(new ActReceiveBomb(aBomb));
    }

    @Override
    public void bombReportFromOponent(BombReport aReport) {
        Engine engine = Engine.getEngine();
        engine.pushAction(new ActReceiveBombReport(aReport));
    }
    
    @Override
    public void readyFromOponent() {
        Engine engine = Engine.getEngine();
        engine.pushAction(new EngineAction(1));
    }

    @Override
    public boolean sendReadyToOponent() {
        if(oponent==null){
            return false;
        }
        oponent.setOponentReady();
        return true;
    }
}
