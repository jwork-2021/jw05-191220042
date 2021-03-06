/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package world;

import asciiPanel.AsciiPanel;

import java.util.List;

/**
 *
 * @author Aeranythe Echosong
 */
public class CreatureFactory {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer(List<String> messages) {
        Creature player = new Creature(this.world, (char)2, AsciiPanel.brightWhite, 5, 20, 5, 5);
        world.addAtEmptyLocation(player);
        new PlayerAI(player, messages,this);
        return player;
    }

    public Creature newFungus() {
        Creature fungus = new Creature(this.world, (char)4, AsciiPanel.green, 10, 0, 0, 0);
        world.addAtEmptyLocation(fungus);
        new FungusAI(fungus, this);
        return fungus;
    }

    public Creature newMonster() {
        Creature monster = new Creature(this.world, (char)1,AsciiPanel.white,10,2,0,0);
        world.addAtEmptyLocation(monster);
        MonsterAI monsterAI = new MonsterAI(monster,this);
        monster.setAI(monsterAI);
        return monster;
    }

    public Creature newPlayerE(PlayerAI hero) {
        Creature playerE = new Creature(this.world, (char)9, AsciiPanel.white, 100, 10, 0, 5);
        playerE.setX(hero.creature.getX());
        playerE.setY(hero.creature.getY());
        this.world.getCreatures().add(playerE);

        PlayerE myE = new PlayerE(playerE, this,hero);
        playerE.setAI(myE);

        return playerE;
    }
}
