package sk.Adin.Anni;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sk.Adin.Anni.object.GameTeam;
import sk.Adin.Anni.object.Kit;
import sk.Adin.Anni.object.PlayerMeta;


public class Util {
    
//public class ParticleEffect {

public enum ParticleEffect {
/**
* A particle effect which is displayed by exploding tnt and creepers:
* <ul>
* <li>It looks like a white cloud
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
EXPLOSION_NORMAL("explode", 0, -1),
/**
* A particle effect which is displayed by exploding ghast fireballs and wither skulls:
* <ul>
* <li>It looks like a gray ball which is fading away
* <li>The speed value slightly influences the size of this particle effect
* </ul>
*/
EXPLOSION_LARGE("largeexplode", 1, -1),
/**
* A particle effect which is displayed by exploding tnt and creepers:
* <ul>
* <li>It looks like a crowd of gray balls which are fading away
* <li>The speed value has no influence on this particle effect
* </ul>
*/
EXPLOSION_HUGE("hugeexplosion", 2, -1),
/**
* A particle effect which is displayed by launching fireworks:
* <ul>
* <li>It looks like a white star which is sparkling
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
FIREWORKS_SPARK("fireworksSpark", 3, -1),
/**
* A particle effect which is displayed by swimming entities and arrows in water:
* <ul>
* <li>It looks like a bubble
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
WATER_BUBBLE("bubble", 4, -1, false, true),
/**
* A particle effect which is displayed by swimming entities and shaking wolves:
* <ul>
* <li>It looks like a blue drop
* <li>The speed value has no influence on this particle effect
* </ul>
*/
WATER_SPLASH("splash", 5, -1),
/**
* A particle effect which is displayed on water when fishing:
* <ul>
* <li>It looks like a blue droplet
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
WATER_WAKE("wake", 6, 7),
/**
* A particle effect which is displayed by water:
* <ul>
* <li>It looks like a tiny blue square
* <li>The speed value has no influence on this particle effect
* </ul>
*/
SUSPENDED("suspended", 7, -1, false, true),
/**
* A particle effect which is displayed by air when close to bedrock and the in the void:
* <ul>
* <li>It looks like a tiny gray square
* <li>The speed value has no influence on this particle effect
* </ul>
*/
SUSPENDED_DEPTH("depthSuspend", 8, -1),
/**
* A particle effect which is displayed when landing a critical hit and by arrows:
* <ul>
* <li>It looks like a light brown cross
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
CRIT("crit", 9, -1),
/**
* A particle effect which is displayed when landing a hit with an enchanted weapon:
* <ul>
* <li>It looks like a cyan star
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
CRIT_MAGIC("magicCrit", 10, -1),
/**
* A particle effect which is displayed by primed tnt, torches, droppers, dispensers, end portals, brewing stands and monster spawners:
* <ul>
* <li>It looks like a little gray cloud
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
SMOKE_NORMAL("smoke", 11, -1),
/**
* A particle effect which is displayed by fire, minecarts with furnace and blazes:
* <ul>
* <li>It looks like a large gray cloud
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
SMOKE_LARGE("largesmoke", 12, -1),
/**
* A particle effect which is displayed when splash potions or bottles o' enchanting hit something:
* <ul>
* <li>It looks like a white swirl
* <li>The speed value causes the particle to only move upwards when set to 0
* </ul>
*/
SPELL("spell", 13, -1),
/**
* A particle effect which is displayed when instant splash potions hit something:
* <ul>
* <li>It looks like a white cross
* <li>The speed value causes the particle to only move upwards when set to 0
* </ul>
*/
SPELL_INSTANT("instantSpell", 14, -1),
/**
* A particle effect which is displayed by entities with active potion effects:
* <ul>
* <li>It looks like a colored swirl
* <li>The speed value causes the particle to be colored black when set to 0
* <li>The particle color gets lighter when increasing the speed and darker when decreasing the speed
* </ul>
*/
SPELL_MOB("mobSpell", 15, -1),
/**
* A particle effect which is displayed by entities with active potion effects applied through a beacon:
* <ul>
* <li>It looks like a transparent colored swirl
* <li>The speed value causes the particle to be always colored black when set to 0
* <li>The particle color gets lighter when increasing the speed and darker when decreasing the speed
* </ul>
*/
SPELL_MOB_AMBIENT("mobSpellAmbient", 16, -1),
/**
* A particle effect which is displayed by witches:
* <ul>
* <li>It looks like a purple cross
* <li>The speed value causes the particle to only move upwards when set to 0
* </ul>
*/
SPELL_WITCH("witchMagic", 17, -1),
/**
* A particle effect which is displayed by blocks beneath a water source:
* <ul>
* <li>It looks like a blue drip
* <li>The speed value has no influence on this particle effect
* </ul>
*/
DRIP_WATER("dripWater", 18, -1),
/**
* A particle effect which is displayed by blocks beneath a lava source:
* <ul>
* <li>It looks like an orange drip
* <li>The speed value has no influence on this particle effect
* </ul>
*/
DRIP_LAVA("dripLava", 19, -1),
/**
* A particle effect which is displayed when attacking a villager in a village:
* <ul>
* <li>It looks like a cracked gray heart
* <li>The speed value has no influence on this particle effect
* </ul>
*/
VILLAGER_ANGRY("angryVillager", 20, -1),
/**
* A particle effect which is displayed when using bone meal and trading with a villager in a village:
* <ul>
* <li>It looks like a green star
* <li>The speed value has no influence on this particle effect
* </ul>
*/
VILLAGER_HAPPY("happyVillager", 21, -1),
/**
* A particle effect which is displayed by mycelium:
* <ul>
* <li>It looks like a tiny gray square
* <li>The speed value has no influence on this particle effect
* </ul>
*/
TOWN_AURA("townaura", 22, -1),
/**
* A particle effect which is displayed by note blocks:
* <ul>
* <li>It looks like a colored note
* <li>The speed value causes the particle to be colored green when set to 0
* </ul>
*/
NOTE("note", 23, -1),
/**
* A particle effect which is displayed by nether portals, endermen, ender pearls, eyes of ender, ender chests and dragon eggs:
* <ul>
* <li>It looks like a purple cloud
* <li>The speed value influences the spread of this particle effect
* </ul>
*/
PORTAL("portal", 24, -1),
/**
* A particle effect which is displayed by enchantment tables which are nearby bookshelves:
* <ul>
* <li>It looks like a cryptic white letter
* <li>The speed value influences the spread of this particle effect
* </ul>
*/
ENCHANTMENT_TABLE("enchantmenttable", 25, -1),
/**
* A particle effect which is displayed by torches, active furnaces, magma cubes and monster spawners:
* <ul>
* <li>It looks like a tiny flame
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
FLAME("flame", 26, -1),
/**
* A particle effect which is displayed by lava:
* <ul>
* <li>It looks like a spark
* <li>The speed value has no influence on this particle effect
* </ul>
*/
LAVA("lava", 27, -1),
/**
* A particle effect which is currently unused:
* <ul>
* <li>It looks like a transparent gray square
* <li>The speed value has no influence on this particle effect
* </ul>
*/
FOOTSTEP("footstep", 28, -1),
/**
* A particle effect which is displayed when a mob dies:
* <ul>
* <li>It looks like a large white cloud
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
CLOUD("cloud", 29, -1),
/**
* A particle effect which is displayed by redstone ore, powered redstone, redstone torches and redstone repeaters:
* <ul>
* <li>It looks like a tiny colored cloud
* <li>The speed value causes the particle to be colored red when set to 0
* </ul>
*/
REDSTONE("reddust", 30, -1),
/**
* A particle effect which is displayed when snowballs hit a block:
* <ul>
* <li>It looks like a little piece with the snowball texture
* <li>The speed value has no influence on this particle effect
* </ul>
*/
SNOWBALL("snowballpoof", 31, -1),
/**
* A particle effect which is currently unused:
* <ul>
* <li>It looks like a tiny white cloud
* <li>The speed value influences the velocity at which the particle flies off
* </ul>
*/
SNOW_SHOVEL("snowshovel", 32, -1),
/**
* A particle effect which is displayed by slimes:
* <ul>
* <li>It looks like a tiny part of the slimeball icon
* <li>The speed value has no influence on this particle effect
* </ul>
*/
SLIME("slime", 33, -1),
/**
* A particle effect which is displayed when breeding and taming animals:
* <ul>
* <li>It looks like a red heart
* <li>The speed value has no influence on this particle effect
* </ul>
*/
HEART("heart", 34, -1),
/**
* A particle effect which is displayed by barriers:
* <ul>
* <li>It looks like a red box with a slash through it
* <li>The speed value has no influence on this particle effect
* </ul>
*/
BARRIER("barrier", 35, 8),
/**
* A particle effect which is displayed when breaking a tool or eggs hit a block:
* <ul>
* <li>It looks like a little piece with an item texture
* </ul>
*/
ITEM_CRACK("iconcrack", 36, -1, true),
/**
* A particle effect which is displayed when breaking blocks or sprinting:
* <ul>
* <li>It looks like a little piece with a block texture
* <li>The speed value has no influence on this particle effect
* </ul>
*/
BLOCK_CRACK("blockcrack", 37, -1, true),
/**
* A particle effect which is displayed when falling:
* <ul>
* <li>It looks like a little piece with a block texture
* </ul>
*/
BLOCK_DUST("blockdust", 38, 7, true),
/**
* A particle effect which is displayed when rain hits the ground:
* <ul>
* <li>It looks like a blue droplet
* <li>The speed value has no influence on this particle effect
* </ul>
*/
WATER_DROP("droplet", 39, 8),
/**
* A particle effect which is currently unused:
* <ul>
* <li>It has no visual effect
* </ul>
*/
ITEM_TAKE("take", 40, 8),
/**
* A particle effect which is displayed by elder guardians:
* <ul>
* <li>It looks like the shape of the elder guardian
* <li>The speed value has no influence on this particle effect
* </ul>
*/
MOB_APPEARANCE("mobappearance", 41, 8);
private static final Map<String, ParticleEffect> NAME_MAP = new HashMap<>();
private static final Map<Integer, ParticleEffect> ID_MAP = new HashMap<>();
private final String name;
private final int id;
private final int requiredVersion;
private final boolean requiresData;
private final boolean requiresWater;
// Initialize map for quick name and id lookup
static {
for (ParticleEffect effect : values()) {
NAME_MAP.put(effect.name, effect);
ID_MAP.put(effect.id, effect);
}
}
/**
* Construct a new particle effect
*
* @param name Name of this particle effect
* @param id Id of this particle effect
* @param requiredVersion Version which is required (1.x)
* @param requiresData Indicates whether additional data is required for this particle effect
* @param requiresWater Indicates whether water is required for this particle effect to display properly
*/
private ParticleEffect(String name, int id, int requiredVersion, boolean requiresData, boolean requiresWater) {
this.name = name;
this.id = id;
this.requiredVersion = requiredVersion;
this.requiresData = requiresData;
this.requiresWater = requiresWater;
}
/**
* Construct a new particle effect with {@link #requiresWater} set to <code>false</code>
*
* @param name Name of this particle effect
* @param id Id of this particle effect
* @param requiredVersion Version which is required (1.x)
* @param requiresData Indicates whether additional data is required for this particle effect
* @see #ParticleEffect(String, int, boolean, boolean)
*/
private ParticleEffect(String name, int id, int requiredVersion, boolean requiresData) {
this(name, id, requiredVersion, requiresData, false);
}
/**
* Construct a new particle effect with {@link #requiresData} and {@link #requiresWater} set to <code>false</code>
*
* @param name Name of this particle effect
* @param id Id of this particle effect
* @param requiredVersion Version which is required (1.x)
* @param requiresData Indicates whether additional data is required for this particle effect
* @see #ParticleEffect(String, int, boolean)
*/
private ParticleEffect(String name, int id, int requiredVersion) {
this(name, id, requiredVersion, false);
}
/**
* Returns the name of this particle effect
*
* @return The name
*/
public String getName() {
return name;
}
/**
* Returns the id of this particle effect
*
* @return The id
*/
public int getId() {
return id;
}
/**
* Returns the required version for this particle effect (1.x)
*
* @return The required version
*/
public int getRequiredVersion() {
return requiredVersion;
}
/**
* Determine if additional data is required for this particle effect
*
* @return Whether additional data is required or not
*/
public boolean getRequiresData() {
return requiresData;
}
/**
* Determine if water is required for this particle effect to display properly
*
* @return Whether water is required or not
*/
public boolean getRequiresWater() {
return requiresWater;
}
/**
* Determine if this particle effect is supported by your current server version
*
* @return Whether the particle effect is supported or not
*/
public boolean isSupported() {
if (requiredVersion == -1) {
return true;
}
return ParticlePacket.getVersion() >= requiredVersion;
}
/**
* Returns the particle effect with the given name
*
* @param name Name of the particle effect
* @return The particle effect
*/
public static ParticleEffect fromName(String name) {
for (Map.Entry<String, ParticleEffect> entry : NAME_MAP.entrySet()) {
if (!entry.getKey().equalsIgnoreCase(name)) {
continue;
}
return entry.getValue();
}
return null;
}
/**
* Returns the particle effect with the given id
*
* @param id Id of the particle effect
* @return The particle effect
*/
public static ParticleEffect fromId(int id) {
for (Map.Entry<Integer, ParticleEffect> entry : ID_MAP.entrySet()) {
if (entry.getKey() != id) {
continue;
}
return entry.getValue();
}
return null;
}
/**
* Determine if water is at a certain location
*
* @param location Location to check
* @return Whether water is at this location or not
*/
private static boolean isWater(Location location) {
Material material = location.getBlock().getType();
return material == Material.WATER || material == Material.STATIONARY_WATER;
}
/**
* Determine if the distance between @param location and one of the players exceeds 256
*
* @param location Location to check
* @return Whether the distance exceeds 256 or not
*/
private static boolean isLongDistance(Location location, List<Player> players) {
for (Player player : players) {
if (player.getLocation().distanceSquared(location) < 65536) {
continue;
}
return true;
}
return false;
}
/**
* Determine if the data type for a particle effect is correct
*
* @param effect Particle effect
* @param data Particle data
* @return Whether the data type is correct or not
*/
private static boolean isDataCorrect(ParticleEffect effect, ParticleData data) {
return ((effect == BLOCK_CRACK || effect == BLOCK_DUST) && data instanceof BlockData) || effect == ITEM_CRACK && data instanceof ItemData;
}

public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (requiresData) {
throw new ParticleDataException("This particle effect requires additional data");
}
if (requiresWater && !isWater(center)) {
throw new IllegalArgumentException("There is no water at the center location");
}
new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, range > 256, null).sendTo(center, range);
}

public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (requiresData) {
throw new ParticleDataException("This particle effect requires additional data");
}
if (requiresWater && !isWater(center)) {
throw new IllegalArgumentException("There is no water at the center location");
}
new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, isLongDistance(center, players), null).sendTo(center, players);
}

public void display(float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player... players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
display(offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
}

public void display(Vector direction, float speed, Location center, double range) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (requiresData) {
throw new ParticleDataException("This particle effect requires additional data");
}
if (requiresWater && !isWater(center)) {
throw new IllegalArgumentException("There is no water at the center location");
}
new ParticlePacket(this, direction, speed, range > 256, null).sendTo(center, range);
}

public void display(Vector direction, float speed, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (requiresData) {
throw new ParticleDataException("This particle effect requires additional data");
}
if (requiresWater && !isWater(center)) {
throw new IllegalArgumentException("There is no water at the center location");
}
new ParticlePacket(this, direction, speed, isLongDistance(center, players), null).sendTo(center, players);
}

public void display(Vector direction, float speed, Location center, Player... players) throws ParticleVersionException, ParticleDataException, IllegalArgumentException {
display(direction, speed, center, Arrays.asList(players));
}

public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, double range) throws ParticleVersionException, ParticleDataException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (!requiresData) {
throw new ParticleDataException("This particle effect does not require additional data");
}
if (!isDataCorrect(this, data)) {
throw new ParticleDataException("The particle data type is incorrect");
}
new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, range > 256, data).sendTo(center, range);
}

public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (!requiresData) {
throw new ParticleDataException("This particle effect does not require additional data");
}
if (!isDataCorrect(this, data)) {
throw new ParticleDataException("The particle data type is incorrect");
}
new ParticlePacket(this, offsetX, offsetY, offsetZ, speed, amount, isLongDistance(center, players), data).sendTo(center, players);
}

public void display(ParticleData data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Location center, Player... players) throws ParticleVersionException, ParticleDataException {
display(data, offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
}

public void display(ParticleData data, Vector direction, float speed, Location center, double range) throws ParticleVersionException, ParticleDataException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (!requiresData) {
throw new ParticleDataException("This particle effect does not require additional data");
}
if (!isDataCorrect(this, data)) {
throw new ParticleDataException("The particle data type is incorrect");
}
new ParticlePacket(this, direction, speed, range > 256, data).sendTo(center, range);
}

public void display(ParticleData data, Vector direction, float speed, Location center, List<Player> players) throws ParticleVersionException, ParticleDataException {
if (!isSupported()) {
throw new ParticleVersionException("This particle effect is not supported by your server version");
}
if (!requiresData) {
throw new ParticleDataException("This particle effect does not require additional data");
}
if (!isDataCorrect(this, data)) {
throw new ParticleDataException("The particle data type is incorrect");
}
new ParticlePacket(this, direction, speed, isLongDistance(center, players), data).sendTo(center, players);
}

public void display(ParticleData data, Vector direction, float speed, Location center, Player... players) throws ParticleVersionException, ParticleDataException {
display(data, direction, speed, center, Arrays.asList(players));
}
/**
* Represents the particle data for effects like {@link ParticleEffect#ITEM_CRACK}, {@link ParticleEffect#BLOCK_CRACK} and {@link ParticleEffect#BLOCK_DUST}
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.6
*/
public static abstract class ParticleData {
private final Material material;
private final byte data;
private final int[] packetData;
/**
* Construct a new particle data
*
* @param material Material of the item/block
* @param data Data value of the item/block
*/
@SuppressWarnings("deprecation")
public ParticleData(Material material, byte data) {
this.material = material;
this.data = data;
this.packetData = new int[] { material.getId(), data };
}
/**
* Returns the material of this data
*
* @return The material
*/
public Material getMaterial() {
return material;
}
/**
* Returns the data value of this data
*
* @return The data value
*/
public byte getData() {
return data;
}
/**
* Returns the data as an int array for packet construction
*
* @return The data for the packet
*/
public int[] getPacketData() {
return packetData;
}
/**
* Returns the data as a string for pre 1.8 versions
*
* @return The data string for the packet
*/
public String getPacketDataString() {
return "_" + packetData[0] + "_" + packetData[1];
}
}
/**
* Represents the item data for the {@link ParticleEffect#ITEM_CRACK} effect
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.6
*/
public static final class ItemData extends ParticleData {
/**
* Construct a new item data
*
* @param material Material of the item
* @param data Data value of the item
* @see ParticleData#ParticleData(Material, byte)
*/
public ItemData(Material material, byte data) {
super(material, data);
}
}
/**
* Represents the block data for the {@link ParticleEffect#BLOCK_CRACK} and {@link ParticleEffect#BLOCK_DUST} effects
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.6
*/
public static final class BlockData extends ParticleData {
/**
* Construct a new block data
*
* @param material Material of the block
* @param data Data value of the block
* @throws IllegalArgumentException If the material is not a block
* @see ParticleData#ParticleData(Material, byte)
*/
public BlockData(Material material, byte data) throws IllegalArgumentException {
super(material, data);
if (!material.isBlock()) {
throw new IllegalArgumentException("The material is not a block");
}
}
}
/**
* Represents a runtime exception that is thrown either if the displayed particle effect requires data and has none or vice-versa or if the data type is wrong
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.6
*/
private static final class ParticleDataException extends RuntimeException {
private static final long serialVersionUID = 3203085387160737484L;
/**
* Construct a new particle data exception
*
* @param message Message that will be logged
* @param cause Cause of the exception
*/
public ParticleDataException(String message) {
super(message);
}
}
/**
* Represents a runtime exception that is thrown if the displayed particle effect requires a newer version
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.6
*/
private static final class ParticleVersionException extends RuntimeException {
private static final long serialVersionUID = 3203085387160737484L;
/**
* Construct a new particle version exception
*
* @param message Message that will be logged
* @param cause Cause of the exception
*/
public ParticleVersionException(String message) {
super(message);
}
}
/**
* Represents a particle effect packet with all attributes which is used for sending packets to the players
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.5
*/
public static final class ParticlePacket {
private static int version;
private static Class<?> enumParticle;
private static Constructor<?> packetConstructor;
private static Method getHandle;
private static Field playerConnection;
private static Method sendPacket;
private static boolean initialized;
private final ParticleEffect effect;
private final float offsetX;
private final float offsetY;
private final float offsetZ;
private final float speed;
private final int amount;
private final boolean longDistance;
private final ParticleData data;
private Object packet;
/**
* Construct a new particle packet
*
* @param effect Particle effect
* @param offsetX Maximum distance particles can fly away from the center on the x-axis
* @param offsetY Maximum distance particles can fly away from the center on the y-axis
* @param offsetZ Maximum distance particles can fly away from the center on the z-axis
* @param speed Display speed of the particles
* @param amount Amount of particles
* @param longDistance Indicates whether the maximum distance is increased from 256 to 65536
* @param data Data of the effect
* @throws IllegalArgumentException If the speed is lower than 0 or the amount is lower than 1
* @see #initialize()
*/
public ParticlePacket(ParticleEffect effect, float offsetX, float offsetY, float offsetZ, float speed, int amount, boolean longDistance, ParticleData data) throws IllegalArgumentException {
initialize();
if (speed < 0) {
throw new IllegalArgumentException("The speed is lower than 0");
}
if (amount < 1) {
throw new IllegalArgumentException("The amount is lower than 1");
}
this.effect = effect;
this.offsetX = offsetX;
this.offsetY = offsetY;
this.offsetZ = offsetZ;
this.speed = speed;
this.amount = amount;
this.longDistance = longDistance;
this.data = data;
}
/**
* Construct a new particle packet of a single particle flying into a determined direction
*
* @param effect Particle effect
* @param direction Direction of the particle
* @param speed Display speed of the particle
* @param longDistance Indicates whether the maximum distance is increased from 256 to 65536
* @param data Data of the effect
* @throws IllegalArgumentException If the speed is lower than 0
* @see #initialize()
*/
public ParticlePacket(ParticleEffect effect, Vector direction, float speed, boolean longDistance, ParticleData data) throws IllegalArgumentException {
initialize();
if (speed < 0) {
throw new IllegalArgumentException("The speed is lower than 0");
}
this.effect = effect;
this.offsetX = (float) direction.getX();
this.offsetY = (float) direction.getY();
this.offsetZ = (float) direction.getZ();
this.speed = speed;
this.amount = 0;
this.longDistance = longDistance;
this.data = data;
}

public static void initialize() throws VersionIncompatibleException {
if (initialized) {
return;
}
try {
version = Integer.parseInt(Character.toString(ReflectionUtils.PackageType.getServerVersion().charAt(3)));
if (version > 7) {
enumParticle = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumParticle");
}
Class<?> packetClass = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass(version < 7 ? "Packet63WorldParticles" : "PacketPlayOutWorldParticles");
packetConstructor = ReflectionUtils.getConstructor(packetClass);
getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle");
playerConnection = ReflectionUtils.getField("EntityPlayer", ReflectionUtils.PackageType.MINECRAFT_SERVER, false, "playerConnection");
sendPacket = ReflectionUtils.getMethod(playerConnection.getType(), "sendPacket", ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet"));
} catch (NumberFormatException | ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException exception) {
throw new VersionIncompatibleException("Your current bukkit version seems to be incompatible with this library", exception);
}
initialized = true;
}
/**
* Returns the version of your server (1.x)
*
* @return The version number
*/
public static int getVersion() {
return version;
}
/**
* Determine if {@link #packetConstructor}, {@link #getHandle}, {@link #playerConnection} and {@link #sendPacket} are initialized
*
* @return Whether these fields are initialized or not
* @see #initialize()
*/
public static boolean isInitialized() {
return initialized;
}
/**
* Sends the packet to a single player and caches it
*
* @param center Center location of the effect
* @param player Receiver of the packet
* @throws PacketInstantiationException if instantion fails due to an unknown error
* @throws PacketSendingException if sending fails due to an unknown error
*/
public void sendTo(Location center, Player player) throws PacketInstantiationException, PacketSendingException {
if (packet == null) {
try {
packet = packetConstructor.newInstance();
Object id;
if (version < 8) {
id = effect.getName() + (data == null ? "" : data.getPacketDataString());
} else {
id = enumParticle.getEnumConstants()[effect.getId()];
}
ReflectionUtils.setValue(packet, true, "a", id);
ReflectionUtils.setValue(packet, true, "b", (float) center.getX());
ReflectionUtils.setValue(packet, true, "c", (float) center.getY());
ReflectionUtils.setValue(packet, true, "d", (float) center.getZ());
ReflectionUtils.setValue(packet, true, "e", offsetX);
ReflectionUtils.setValue(packet, true, "f", offsetY);
ReflectionUtils.setValue(packet, true, "g", offsetZ);
ReflectionUtils.setValue(packet, true, "h", speed);
ReflectionUtils.setValue(packet, true, "i", amount);
if (version > 7) {
ReflectionUtils.setValue(packet, true, "j", longDistance);
ReflectionUtils.setValue(packet, true, "k", data == null ? new int[0] : data.getPacketData());
}
} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException exception) {
throw new PacketInstantiationException("Packet instantiation failed", exception);
}
}
try {
sendPacket.invoke(playerConnection.get(getHandle.invoke(player)), packet);
} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
throw new PacketSendingException("Failed to send the packet to player '" + player.getName() + "'", exception);
}
}
/**
* Sends the packet to all players in the list
*
* @param center Center location of the effect
* @param players Receivers of the packet
* @throws IllegalArgumentException If the player list is empty
* @see #sendTo(Location center, Player player)
*/
public void sendTo(Location center, List<Player> players) throws IllegalArgumentException {
if (players.isEmpty()) {
throw new IllegalArgumentException("The player list is empty");
}
for (Player player : players) {
sendTo(center, player);
}
}
/**
* Sends the packet to all players in a certain range
*
* @param center Center location of the effect
* @param range Range in which players will receive the packet (Maximum range for particles is usually 16, but it can differ for some types)
* @throws IllegalArgumentException If the range is lower than 1
* @see #sendTo(Location center, Player player)
*/
public void sendTo(Location center, double range) throws IllegalArgumentException {
if (range < 1) {
throw new IllegalArgumentException("The range is lower than 1");
}
String worldName = center.getWorld().getName();
double squared = range * range;
for (Player player : Bukkit.getOnlinePlayers()) {
if (!player.getWorld().getName().equals(worldName) || player.getLocation().distanceSquared(center) > squared) {
continue;
}
sendTo(center, player);
}
}
/**
* Represents a runtime exception that is thrown if a bukkit version is not compatible with this library
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.5
*/
private static final class VersionIncompatibleException extends RuntimeException {
private static final long serialVersionUID = 3203085387160737484L;
/**
* Construct a new version incompatible exception
*
* @param message Message that will be logged
* @param cause Cause of the exception
*/
public VersionIncompatibleException(String message, Throwable cause) {
super(message, cause);
}
}
/**
* Represents a runtime exception that is thrown if packet instantiation fails
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.4
*/
private static final class PacketInstantiationException extends RuntimeException {
private static final long serialVersionUID = 3203085387160737484L;
/**
* Construct a new packet instantiation exception
*
* @param message Message that will be logged
* @param cause Cause of the exception
*/
public PacketInstantiationException(String message, Throwable cause) {
super(message, cause);
}
}
/**
* Represents a runtime exception that is thrown if packet sending fails
* <p>
* This class is part of the <b>ParticleEffect Library</b> and follows the same usage conditions
*
* @author DarkBlade12
* @since 1.4
*/
private static final class PacketSendingException extends RuntimeException {
private static final long serialVersionUID = 3203085387160737484L;
/**
* Construct a new packet sending exception
*
* @param message Message that will be logged
* @param cause Cause of the exception
*/
public PacketSendingException(String message, Throwable cause) {
super(message, cause);

           }
       }
   }
    }

private String realName;
private int id;

Util(String realName, int id) {
this.realName = realName;
this.id = id;
}

/**
* Gets the name of the Particle Effect
*
* @return The particle effect name
*/
String getName() {
return realName;
}

/**
* Gets the id of the Particle Effect
*
* @return The id of the Particle Effect
*/
int getId() {
return id;
}

public static void sendToPlayer(Util effect, Player player, Location location, float offsetX, float offsetY,
float offsetZ, float speed, int count) {
try {
Object packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
sendPacket(player, packet);
} catch (Exception e) {
e.printStackTrace();
}

}


public static void sendToLocation(Util effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) {
try {
Object packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
for (Player player : Bukkit.getOnlinePlayers()) {
sendPacket(player, packet);
}
} catch (Exception e) {
e.printStackTrace();
}
}

private static Object createPacket(Util effect, Location location, float offsetX, float offsetY,
float offsetZ, float speed, int count) throws Exception {
if (count <= 0) {
count = 1;
}
Class<?> packetClass = getCraftClass("PacketPlayOutWorldParticles");
Object packet = packetClass.getConstructor(EnumParticle.class, boolean.class, float.class, float.class,
float.class, float.class, float.class, float.class, float.class, int.class,
int[].class).newInstance(EnumParticle.valueOf(effect.realName), true,
(float) location.getX(), (float) location.getY(), (float) location.getZ(),
offsetX, offsetY, offsetZ, speed, count, null);
return packet;
}

private static void sendPacket(Player p, Object packet) throws Exception {
Object eplayer = getHandle(p);
Field playerConnectionField = eplayer.getClass().getField("playerConnection");
Object playerConnection = playerConnectionField.get(eplayer);
for (Method m : playerConnection.getClass().getMethods()) {
if (m.getName().equalsIgnoreCase("sendPacket")) {
m.invoke(playerConnection, packet);
return;
}
}
}

private static Object getHandle(Entity entity) {
try {
Method entity_getHandle = entity.getClass().getMethod("getHandle");
Object nms_entity = entity_getHandle.invoke(entity);
return nms_entity;
} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
ex.printStackTrace();
return null;
}
}

private static Class<?> getCraftClass(String name) {
String version = getVersion() + ".";
String className = "net.minecraft.server." + version + name;
Class<?> clazz = null;
try {
clazz = Class.forName(className);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
return clazz;
}

private static String getVersion() {
return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
}

public static Location parseLocation(World w, String in) {
String[] params = in.split(",");
for (String s : params)
s.replace("-0", "0");
if (params.length == 3 || params.length == 5) {
double x = Double.parseDouble(params[0]);
double y = Double.parseDouble(params[1]);
double z = Double.parseDouble(params[2]);
Location loc = new Location(w, x, y, z);
if (params.length == 5) {
loc.setYaw(Float.parseFloat(params[4]));
loc.setPitch(Float.parseFloat(params[5]));
}
return loc;
}
return null;
}

public static void sendPlayerToGame(final Player player, Anni plugin) {
final PlayerMeta meta = PlayerMeta.getMeta(player);
if (meta.getTeam() != null) {
meta.setAlive(true);
player.teleport(meta.getTeam().getRandomSpawn());

Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
@Override
public void run() {
meta.getKit().give(player, meta.getTeam());
player.setCompassTarget(meta.getTeam().getNexus().getLocation());
player.setGameMode(GameMode.SURVIVAL);
player.setHealth(player.getMaxHealth());
player.setFoodLevel(20);
player.setSaturation(20F);
}
}, 10L);
}
}

public static boolean isEmptyColumn(Location loc) {
boolean hasBlock = false;
Location test = loc.clone();
for (int y = 0; y < loc.getWorld().getMaxHeight(); y++) {
test.setY(y);
if (test.getBlock().getType() != Material.AIR)
hasBlock = true;
}
return !hasBlock;
}

public static void showClassSelector(Player p, String title) {
int size = ((Kit.values().length + 8) / 9) * 9;
Inventory inv = Bukkit.createInventory(p, size, title);
for (Kit kit : Kit.values()) {
ItemStack i = kit.getIcon().clone();
ItemMeta im = i.getItemMeta();
List<String> lore = im.getLore();
lore.add(ChatColor.GRAY + "---------------");
if (kit.isOwnedBy(p)) {
lore.add(ChatColor.GREEN + "������");
} else {
lore.add(ChatColor.RED + "������");
}
im.setLore(lore);
i.setItemMeta(im);
inv.addItem(i);
}
p.openInventory(inv);
}

public static void spawnFirework(Location loc) {
Random colour = new Random();

Firework fw = loc.getWorld().spawn(loc, Firework.class);
FireworkMeta fwMeta = fw.getFireworkMeta();
Type fwType = Type.BALL_LARGE;

int c1i = colour.nextInt(17) + 1;
int c2i = colour.nextInt(17) + 1;

Color c1 = getFWColor(c1i);
Color c2 = getFWColor(c2i);

FireworkEffect effect = FireworkEffect.builder().withFade(c2).withColor(c1).with(fwType).build();

fwMeta.addEffect(effect);
fwMeta.setPower(1);
fw.setFireworkMeta(fwMeta);
}

public static void spawnFirework(Location loc, Color c1, Color c2) {
Firework fw = loc.getWorld().spawn(loc, Firework.class);
FireworkMeta fwMeta = fw.getFireworkMeta();

Type fwType = Type.BALL_LARGE;

FireworkEffect effect = FireworkEffect.builder().withFade(c2).withColor(c1).with(fwType).build();

fwMeta.addEffect(effect);
fwMeta.setPower(1);
fw.setFireworkMeta(fwMeta);
}

public static Color getFWColor(int c) {
switch (c) {
case 1:
return Color.TEAL;
default:
case 2:
return Color.WHITE;
case 3:
return Color.YELLOW;
case 4:
return Color.AQUA;
case 5:
return Color.BLACK;
case 6:
return Color.BLUE;
case 7:
return Color.FUCHSIA;
case 8:
return Color.GRAY;
case 9:
return Color.GREEN;
case 10:
return Color.LIME;
case 11:
return Color.MAROON;
case 12:
return Color.NAVY;
case 13:
return Color.OLIVE;
case 14:
return Color.ORANGE;
case 15:
return Color.PURPLE;
case 16:
return Color.RED;
case 17:
return Color.SILVER;
}
}

public static String getPhaseColor(int phase) {
switch (phase) {
case 1:
return ChatColor.BLUE.toString();
case 2:
return ChatColor.GREEN.toString();
case 3:
return ChatColor.YELLOW.toString();
case 4:
return ChatColor.GOLD.toString();
case 5:
return ChatColor.RED.toString();
default:
return ChatColor.WHITE.toString();
}
}

public static boolean isTeamTooBig(GameTeam team) {
	int players = team.getPlayers().size();
	for (GameTeam gt : GameTeam.teams())
		if (players >= gt.getPlayers().size() + 3 && gt.getNexus().isAlive())
			return true;
	return false;
	}

@SuppressWarnings("deprecation")
public static void giveTeamSelector(Player player){
	Anni plugin = Anni.getInstance();
	String item = plugin.getConfig().getString("JoinItemTeamName").replace("&", "�");
	int id = plugin.getConfig().getInt("ItemTeamID");
	int slot = plugin.getConfig().getInt("JoinItemTeamSlot");
	ItemStack selector = new ItemStack(Material.getMaterial(id));
    ItemMeta itemMeta = selector.getItemMeta();
    itemMeta.setDisplayName(item);
    selector.setItemMeta(itemMeta);
    player.getInventory().setItem(slot-1, selector);
    player.updateInventory();
}
}
