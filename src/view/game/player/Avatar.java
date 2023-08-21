package view.game.player;

import java.io.File;
import java.util.*;
/**
 * This enum contains the 14 default avatars, and it is mainly used for picking random avatars for the computer-controlled players to be used.
 * I chose some of the most iconic Pokemon.
 * @author pierp
 *
 */
public enum Avatar {
	/**
	 * The record for Abra.
	 */
	abra("abra",0),
	/**
	 * The record for Bulbasaur.
	 */
	bulbasaur("bulbasaur",1),
	/**
	 * The record for Chansey.
	 */
	chansey("chansey",2),
	/**
	 * The record for Charmander.
	 */
	charmander("charmander",3),
	/**
	 * The record for Eevee.
	 */
	eevee("eevee",4),
	/**
	 * The record for Gastly.
	 */
	gastly("gastly",5),
	/**
	 * The record for Machop.
	 */
	machop("machop",6),
	/**
	 * The record for Magikarp.
	 */
	magikarp("magikarp",7),
	/**
	 * The record for Meowth.
	 */
	meowth("meowth",8),
	/**
	 * The record for Mew.
	 */
	mew("mew",9),
	/**
	 * The record for Mewtwo.
	 */
	mewtwo("mewtwo",10),
	/**
	 * The record for Pikachu.
	 */
	pikachu("pikachu",11),
	/**
	 * The record for Rattata.
	 */
	rattata("rattata",12),
	/**
	 * The record for Squirtle.
	 */
	squirtle("squirtle",13);
	
	/**
	 * The name of the avatar.
	 */
	private String name;
	/**
	 * The index of the avatar.
	 */
	private int index;
	/**
	 * A private constructor for the enum.
	 * @param name The name of the avatar.
	 * @param index The index of the avatar.
	 */
	private Avatar(String name, int index) {
		this.name = name;
		this.index = index;
	}
	/**
	 * This method creates a file containing the image of the avatar.
	 * @return The file.
	 */
	private File toFile() {
		return new File("src/resources/avatars/"+name+".png");
	}
	/**
	 * This method is used to get three random avatars from this enum different from the one already chosen by the user.
	 * @param f The file containing the avatar of the User.
	 * @return An array containing the files of three avatars different from the one of the user and from each other.
	 */
	public static File[] getThreeRandomAvatars(File f) {
		String existingAvatar = (f.toString().split("\\\\")[f.toString().split("\\\\").length-1])
									.substring(0, f.toString().split("\\\\")
									[f.toString().split("\\\\").length-1].length()-4);
		
		int[] indexes = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		ArrayList<Integer> l = new ArrayList<>();
		for(int i: indexes) if(valueOf(existingAvatar) != null && !(i == valueOf(existingAvatar).index)) l.add(i);
		Collections.shuffle(l);
		File[] randomAvatars = {(Avatar.values()[l.get(0)]).toFile(), (Avatar.values()[l.get(1)]).toFile(), (Avatar.values()[l.get(2)]).toFile()};
		return randomAvatars;
	}
}
