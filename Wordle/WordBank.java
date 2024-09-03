import java.util.*;
import java.util.Map;
import java.util.Set;

// WordBank class manages the storage and retrieval of words and their associated hints.
public class WordBank {
    // Maps each letter to a set of words that start with that letter
    private Map<Character, Set<String>> wordsByLetter = new HashMap<>();
    // Maps words to their associated hints
    private Map<String, String> wordHints = new HashMap<>();

    // Returns the mapping of letters to words for external use if needed
    public Map<Character, Set<String>> getWordsByLetter() {
        return wordsByLetter;
    }

    // Constructor that initializes the word bank and hints
    public WordBank() {
        initializeWordBank();
        initializeWordHints();
    }

    // Initializes the word bank with a predefined list of words categorized by their initial letter
    private void initializeWordBank() {
        // Initialize words and categorize them by the first letter
        // Array of word arrays, each sub-array contains words starting with the same letter
        String[][] wordArrays = {
                {"Apple", "Arrow", "Ample", "Amigo", "Amaze", "Alias", "Alive", "Alloy", "Angry", "Adept", "Adore", "Adorn", "Alarm", "Adapt", "Acorn"},
                {"Bacon", "Baker", "Badge", "Baldy", "Bandy", "Barge", "Baron", "Beach", "Belly", "Berry", "Bison", "Bland", "Blast", "Blaze", "Blend"},
                {"Cabin", "Cable", "Candy", "Caper", "Carry", "Charm", "Chase", "Chess", "Civil", "Claim", "Clamp", "Clever", "Cliff", "Cling", "Clove"},
                {"Doggy", "Dwarf", "Dodge", "Diver", "Drape", "Dance", "Drift", "Dress", "Dwell", "Daisy", "Dozen", "Deity", "Dodge", "Dregs", "Drool"},
                {"Eager", "Eagle", "Early", "Earth", "Evoke", "Egret", "Erase", "Equip", "Error", "Esker", "Elbow", "Elect", "Elder", "Empty", "Emote"},
                {"Fable", "Faint", "False", "Fancy", "Farce", "Favor", "Feast", "Fence", "Ferry", "Fiber", "Ficus", "Fjord", "Flame", "Flush", "Frost"},
                {"Giant", "Given", "Glaze", "Globe", "Glory", "Grace", "Grain", "Gravy", "Great", "Green", "Greet", "Grove", "Guard", "Guest", "Guide"},
                {"Happy", "Heath", "Honor", "Hasty", "Hedge", "Honor", "Hello", "Hatch", "Haven", "Hinge", "Honey", "Habit", "Human", "Hurry", "Horse"},
                {"Inbox", "Image", "Index", "Input", "Irony", "Ivory", "Icing", "Idyll", "Idiot", "Impel", "Inset", "Itchy", "Infer", "Imbue", "Islet"},
                {"Jolly", "Joint", "Jumbo", "Jumpy", "Judge", "Juice", "Jewel", "Jiffy", "Joker", "Joust", "Jaunt", "Jaded", "Jabot", "Jetty", "Joule"},
                {"Kneel", "Knack", "Knoll", "Knife", "Kiosk", "Karma", "Kebab", "Kayak", "Kefir", "Kinky", "Kiosk", "Koala", "Krill", "Knave", "Ketch"},
                {"Lemon", "Latch", "Light", "Lunar", "Labor", "Lapse", "Laser", "Laugh", "Leach", "Leery", "Lemma", "Limbo", "Lucid", "Lusty", "Lyric"},
                {"Mango", "Mirth", "Mould", "Mural", "Musky", "Mocha", "Mossy", "Motel", "Mover", "Mulch", "Mummy", "Munch", "Muzzy", "Maxim", "Mince"},
                {"Noble", "Notch", "Nylon", "Nymph", "Nadir", "Nasal", "Natty", "Naval", "Needy", "Neigh", "Nerve", "Newly", "Ninja", "Nudge", "Nonce"},
                {"Ocean", "Opera", "Orbit", "Oxide", "Owner", "Ocher", "Oddly", "Offer", "Often", "Olden", "Olive", "Ombre", "Onset", "Otter", "Ounce"},
                {"Piano", "Pluck", "Plump", "Poise", "Porch", "Proud", "Prune", "Pulse", "Punch", "Pupal", "Puree", "Pique", "Peach", "Pilaf", "Pivot"},
                {"Quota", "Quark", "Queue", "Quilt", "Quite", "Quack", "Quick", "Quirk", "Queen", "Quail", "Quest", "Quill", "Query", "Quell", "Quash"},
                {"Radar", "Radio", "Razor", "Rally", "Ranch", "Rapid", "Ravel", "Raven", "Ready", "Rebel", "Ridge", "Rifle", "Rigid", "Rider", "Rumba"},
                {"Scale", "Scarf", "Scary", "Scene", "Scope", "Score", "Screw", "Sheep", "Shelf", "Shine", "Shirt", "Shock", "Short", "Shout", "Sight"},
                {"Table", "Tango", "Taste", "Teach", "Tears", "Tempt", "Thank", "Think", "Thorn", "Three", "Tiger", "Title", "Torch", "Touch", "Trace"},
                {"Umbra", "Unity", "Upper", "Usage", "Usual", "Ultra", "Under", "Unite", "Usher", "Until", "Urban", "Upset", "Usage", "Usurp"},
                {"Value", "Vapor", "Vocal", "Vivid", "Vital", "Voice", "Vouch", "Visit", "Vibes", "Vague", "Venue", "Vista", "Vault", "Vigor", "Vexed"},
                {"Watch", "Waltz", "Waste", "Water", "Weave", "Weird", "Widow", "Witty", "Woken", "Woven", "Wrist", "Write", "Wrong", "Wrink", "Wreck"},
                {"Xenon", "Xerox", "Xylem", "Xenon", "Xebec", "Xeric", "Xerus", "Xenon", "Xerox", "Xenon", "Xylem", "Xenon", "Xenon", "Xerox", "Xenon"}, // X placeholder words
                {"Yacht", "Yield", "Yodel", "Young", "Yucca", "Youth", "Yummy", "Yeast", "Years", "Yacht", "Yogic", "Yokel", "Yokan", "Yonic", "Yacht"},
                {"Zebra", "Zesty", "Zonal", "Zoned", "Zippo", "Zincs", "Zooks", "Zeros", "Zills", "Zooks", "Zonal", "Zesty", "Zebra", "Zippy", "Zooms"}  // Z placeholder words
            };
        
        // Populate the wordsByLetter map with words from wordArrays
        for (String[] words : wordArrays) {
            for (String word : words) {
                char firstLetter = Character.toLowerCase(word.charAt(0));
                
                // If no set exists for the letter, create one and add the word
                wordsByLetter.putIfAbsent(firstLetter, new HashSet<>());
                wordsByLetter.get(firstLetter).add(word.toLowerCase());
            }
        }
    }
   
    
    // Initializes the hints for each word
    private void initializeWordHints() {
        // Each word is mapped to a hint that describes or defines it
        wordHints.put("apple", "A fruit associated with Newton and a tech giant.");
        wordHints.put("arrow", "It flies straight when released from a bow.");
        wordHints.put("ample", "Sufficient or more than enough.");
        wordHints.put("amigo", "A Spanish term for a friend.");
        wordHints.put("amaze", "To astonish or greatly surprise.");
        wordHints.put("alias", "A false name used to conceal one's identity.");
        wordHints.put("alive", "Having life; living.");
        wordHints.put("alloy", "A metal made by combining two or more metallic elements.");
        wordHints.put("angry", "Feeling or showing strong annoyance.");
        wordHints.put("adept", "Very skilled or proficient at something.");
        wordHints.put("adore", "Love and respect deeply.");
        wordHints.put("adorn", "Make more beautiful or attractive.");
        wordHints.put("alarm", "An anxious awareness of danger.");
        wordHints.put("adapt", "Make suitable for a new use or purpose.");
        wordHints.put("acorn", "The nut of the oak tree.");

        // B
        wordHints.put("bacon", "A type of salt-cured pork.");
        wordHints.put("baker", "Someone who bakes and sells bread, cakes.");
        wordHints.put("badge", "A distinctive emblem worn as a mark of office.");
        wordHints.put("baldy", "Informal term for someone who has little or no hair.");
        wordHints.put("bandy", "To pass back and forth or exchange.");
        wordHints.put("barge", "A long flat-bottomed boat for carrying freight.");
        wordHints.put("baron", "A member of the lowest order of the British nobility.");
        wordHints.put("beach", "A pebbly or sandy shore by the ocean.");
        wordHints.put("belly", "The stomach, especially when prominent.");
        wordHints.put("berry", "A small roundish juicy fruit without a stone.");
        wordHints.put("bison", "A humpbacked shaggy-haired wild ox native to North America and Europe.");
        wordHints.put("bland", "Lacking strong features or characteristics, unseasoned.");
        wordHints.put("blast", "A destructive wave of highly compressed air spreading outward from an explosion.");
        wordHints.put("blaze", "A very large or fiercely burning fire.");
        wordHints.put("blend", "Mix a substance with another substance.");

        // C (partial for demonstration)
        wordHints.put("cabin", "A small shelter or house, made of wood and situated in a wild or remote area.");
        wordHints.put("cable", "A thick rope of wire or nonmetallic fiber, typically used for construction, mooring ships, and towing vehicles.");
        wordHints.put("candy", "A sweet food made with sugar or syrup combined with fruit, chocolate, or nuts.");
        wordHints.put("caper", "Skip or dance about in a lively or playful way.");
        wordHints.put("carry", "Support and move someone or something from one place to another.");
        wordHints.put("charm", "A pleasing or attractive feature or characteristic.");
        wordHints.put("chase", "Pursue in order to catch or catch up with.");
        wordHints.put("chess", "A board game of strategic skill for two players, played on a checkered board.");
        wordHints.put("civil", "Relating to ordinary citizens and their concerns.");
        wordHints.put("claim", "State or assert that something is the case.");
        wordHints.put("clamp", "A device used to hold an object in place.");
        wordHints.put("clever", "Quick to understand, learn, and devise or apply ideas.");
        wordHints.put("cliff", "A steep rock face, especially at the edge of the sea.");
        wordHints.put("cling", "Hold on tightly to.");
        wordHints.put("clove", "A spicy smelling clove tree with aromatic seeds used as spice.");
        wordHints.put("doggy", "A diminutive form of a canine.");
        wordHints.put("dwarf", "A member of a mythical race of short, stocky human-like creatures.");
        wordHints.put("dodge", "Avoid by a sudden quick movement.");
        wordHints.put("diver", "A person who dives as a sport.");
        wordHints.put("drape", "Arrange (cloth or clothing) loosely or casually on or around something.");
        wordHints.put("dance", "Move rhythmically to music, typically following a set sequence of steps.");
        wordHints.put("drift", "Be carried slowly by a current of air or water.");
        wordHints.put("dress", "Put on one's clothes.");
        wordHints.put("dwell", "Live in or at a specified place.");
        wordHints.put("daisy", "A small grassland plant that has flowers with a yellow disk and white rays.");
        wordHints.put("dozen", "A group or set of twelve.");
        wordHints.put("deity", "A god or goddess.");
        wordHints.put("dregs", "The remnants of a liquid left in a container, together with any sediment.");
        wordHints.put("drool", "Drop saliva uncontrollably from the mouth.");

        // E
        wordHints.put("eager", "Very keen and enthusiastic.");
        wordHints.put("eagle", "A large bird of prey with a massive hooked bill and broad wings.");
        wordHints.put("early", "Happening or done before the usual or expected time.");
        wordHints.put("earth", "The third planet from the sun in our solar system.");
        wordHints.put("evoke", "To bring a feeling, memory, or image into the mind.");
        wordHints.put("egret", "A type of heron with a mainly white plumage, having long plumes in the breeding season.");
        wordHints.put("erase", "To remove something completely.");
        wordHints.put("equip", "To provide with necessary materials or gear.");
        wordHints.put("error", "A mistake.");
        wordHints.put("esker", "A long ridge of gravel deposited by a stream flowing under a glacier.");
        wordHints.put("elbow", "The joint connecting the forearm to the upper arm.");
        wordHints.put("elect", "To choose someone officially for a job or responsibility.");
        wordHints.put("elder", "A person who is older than you, often used to convey respect.");
        wordHints.put("empty", "Containing nothing.");
        wordHints.put("emote", "To show emotion in a theatrical manner.");

        // F
        wordHints.put("fable", "A short fictional story with a moral, often featuring animals as characters.");
        wordHints.put("faint", "Lacking clarity, brightness, or loudness.");
        wordHints.put("false", "Not true or correct; erroneous.");
        wordHints.put("fancy", "Elaborate in structure or decoration, often implying sophistication or decorative excess.");
        wordHints.put("farce", "A comic dramatic piece that uses highly exaggerated and funny situations.");
        wordHints.put("favor", "An act of kindness beyond what is usual or owed.");
        wordHints.put("feast", "A large meal, typically a celebratory one.");
        wordHints.put("fence", "A barrier, railing, or other upright structure intended to prevent movement across a boundary.");
        wordHints.put("ferry", "A boat or ship for transporting people and vehicles across a body of water, especially as a regular service.");
        wordHints.put("fiber", "A thread or filament from which a textile is formed.");
        wordHints.put("ficus", "A genus of about 850 species of woody trees, shrubs, and vines in the family Moraceae, commonly known as fig trees.");
        wordHints.put("fjord", "A long, narrow, deep inlet of the sea between high cliffs, typically formed by submergence of a glaciated valley.");
        wordHints.put("flame", "The visible, gaseous part of a fire.");
        wordHints.put("flush", "When a surface becomes level with another surface, or the sudden rush of water in a toilet.");
        wordHints.put("frost", "A thin layer of ice on a solid surface, which forms from water vapor in an above freezing atmosphere.");

        // G
        wordHints.put("giant", "A mythical being of enormous size or a very large person.");
        wordHints.put("given", "Something that is assumed or accepted as true without controversy or question.");
        wordHints.put("glaze", "A glossy finish applied to pottery or the act of becoming glassy or shiny.");
        wordHints.put("globe", "A spherical representation of the Earth or another celestial body.");
        wordHints.put("glory", "High renown or honor won by notable achievements.");
        wordHints.put("grace", "Simple elegance or refinement of movement; courteous goodwill.");
        wordHints.put("grain", "The seeds of cereal plants, or the direction or texture of fibers in wood.");
        wordHints.put("gravy", "The juices that drip from cooking meats, often thickened and seasoned and served as a sauce.");
        wordHints.put("great", "Of an extent, amount, or intensity considerably above the normal or average.");
        wordHints.put("green", "The color between blue and yellow in the spectrum; colored like grass or emeralds.");
        wordHints.put("greet", "To give a polite word or sign of welcome or recognition to someone on arrival.");
        wordHints.put("grove", "A small group of trees, especially one that is cultivated or maintained.");
        wordHints.put("guard", "A person who keeps watch, especially a soldier or security guard.");
        wordHints.put("guest", "A person who is invited to visit someone's home or attend an event.");
        wordHints.put("guide", "A person who advises or shows the way to others, often tourists or travelers.");
        // H
        wordHints.put("happy", "Feeling or showing pleasure or contentment.");
        wordHints.put("heath", "An area of open uncultivated land with characteristic vegetation of heather, low shrubs, and grasses.");
        wordHints.put("honor", "High respect; great esteem or a privilege.");
        wordHints.put("hasty", "Done with excessive speed or urgency; hurried.");
        wordHints.put("hedge", "A fence or boundary formed by closely growing bushes or shrubs.");
        wordHints.put("hello", "A greeting used when meeting someone or acknowledging someone’s arrival or presence.");
        wordHints.put("hatch", "To emerge from an egg or make an egg crack open.");
        wordHints.put("haven", "A place of safety or refuge.");
        wordHints.put("hinge", "A movable joint or mechanism on which a door, gate, or lid swings as it opens and closes.");
        wordHints.put("honey", "A sweet, sticky yellow-brown fluid made by bees and other insects from nectar collected from flowers.");
        wordHints.put("habit", "A regular practice or routine.");
        wordHints.put("human", "Of, relating to, or characteristic of people or human beings.");
        wordHints.put("hurry", "To move or do things more quickly than normal or to cause to move hurriedly.");
        wordHints.put("horse", "A solid-hoofed plant-eating domesticated mammal with a flowing mane and tail, used for riding, racing, and to carry and pull loads.");

        // I
        wordHints.put("inbox", "A box or tray in which incoming mail is placed, especially in an office or by a computer.");
        wordHints.put("image", "A representation of the external form of a person or thing in art.");
        wordHints.put("index", "An alphabetical list of names, subjects, etc., with references to the places where they occur, typically found at the end of a book.");
        wordHints.put("input", "What is put in, taken in, or operated on by any process or system.");
        wordHints.put("irony", "The expression of one's meaning by using language that normally signifies the opposite, typically for humorous or emphatic effect.");
        wordHints.put("ivory", "A hard creamy-white substance composing the main part of the tusks of an elephant, walrus, or narwhal, often (especially formerly) used to make ornaments and other articles.");
        wordHints.put("icing", "A mixture of sugar with water, egg white, or butter, used as a coating for cakes or biscuits.");
        wordHints.put("idyll", "An extremely happy, peaceful, or picturesque episode or scene, typically an idealized or unsustainable one.");
        wordHints.put("idiot", "A stupid person or someone showing a complete lack of thought or common sense.");
        wordHints.put("impel", "Drive, force, or urge someone to do something.");
        wordHints.put("inset", "A thing that is put in or inserted.");
        wordHints.put("itchy", "Having or causing an itchy sensation.");
        wordHints.put("infer", "Deduce or conclude information from evidence and reasoning rather than from explicit statements.");
        wordHints.put("imbue", "Inspire or permeate with a feeling or quality.");
        wordHints.put("islet", "A small island.");

        // J
        wordHints.put("jolly", "Full of high spirits and cheerfulness.");
        wordHints.put("joint", "A point at which parts of an artificial structure are joined.");
        wordHints.put("jumbo", "Unusually large and powerful.");
        wordHints.put("jumpy", "Anxious and uneasy.");
        wordHints.put("judge", "A public official appointed to decide cases in a court of law.");
        wordHints.put("juice", "The liquid that can be extracted from plants.");
        wordHints.put("jewel", "A precious stone, typically a single crystal, used to make decorative pieces.");
        wordHints.put("jiffy", "A very short space of time; a moment.");
        wordHints.put("joker", "A person who is fond of joking or a playing card, typically used as a wild card in games.");
        wordHints.put("joust", "A sports contest in which two opponents on horseback fight with lances.");
        wordHints.put("jaunt", "A short excursion or journey for pleasure.");
        wordHints.put("jaded", "Tired, bored, or lacking enthusiasm, typically after having had too much of something.");
        wordHints.put("jabot", "A decorative frill or ruffle on the front of a shirt or blouse, typically made of lace.");
        wordHints.put("jetty", "A landing stage or small pier at which boats can dock or be moored.");
        wordHints.put("joule", "A unit of energy in the international system of units.");

        // K
        wordHints.put("kneel", "To fall or rest on the knees.");
        wordHints.put("knack", "An acquired or natural skill at performing a task.");
        wordHints.put("knoll", "A small hill or mound.");
        wordHints.put("knife", "An instrument composed of a blade fixed into a handle, used for cutting or as a weapon.");
        wordHints.put("kiosk", "A small open-fronted hut or cubicle from which newspapers, refreshments, tickets, etc., are sold.");
        wordHints.put("karma", "The sum of a person's actions in this and previous states of existence, viewed as deciding their fate in future existences.");
        wordHints.put("kebab", "Pieces of meat, fish, or vegetables roasted or grilled on a skewer or spit.");
        wordHints.put("kayak", "A canoe of a type used originally by the Inuit, made of a light frame with a watertight covering having a small opening in the top to sit in.");
        wordHints.put("kefir", "A sour-tasting drink made from cow's milk fermented with certain bacteria.");
        wordHints.put("kinky", "Used of hair that is tightly curled or involving or given to unusual sexual behavior.");
        wordHints.put("koala", "A tree-dwelling marsupial of coastal eastern Australia that resembles a small bear with a broad head, large ears, and sharp claws.");
        wordHints.put("krill", "Small shrimplike planktonic crustaceans of the open seas, a major food source for many fish, whales, and birds.");
        wordHints.put("knave", "A dishonest or unscrupulous man.");
        wordHints.put("ketch", "A sailing vessel with two masts, a mainmast and a shorter mizzenmast abaft of the steering wheel.");
        // L
        wordHints.put("lemon", "A bright yellow citrus fruit known for its sour taste.");
        wordHints.put("latch", "A type of fastening that allows a door to be closed securely.");
        wordHints.put("light", "The natural agent that stimulates sight and makes things visible.");
        wordHints.put("lunar", "Relating to the moon.");
        wordHints.put("labor", "Work, especially hard physical work.");
        wordHints.put("lapse", "A temporary failure of concentration, memory, or judgment.");
        wordHints.put("laser", "A device that emits light through a process of optical amplification.");
        wordHints.put("laugh", "Make the spontaneous sounds and movements of the face and body that are the instinctive expressions of lively amusement.");
        wordHints.put("leach", "To cause (a liquid) to drain or seep through a porous material or filter.");
        wordHints.put("leery", "Cautious or wary due to realistic suspicions.");
        wordHints.put("lemma", "A subsidiary or intermediate theorem in an argument or proof.");
        wordHints.put("limbo", "An uncertain period of awaiting a decision or resolution; an intermediate state or condition.");
        wordHints.put("lucid", "Expressed clearly; easy to understand.");
        wordHints.put("lusty", "Healthy and strong; full of vigor.");
        wordHints.put("lyric", "Expressing the writer's emotions, usually briefly and in stanzas or recognized forms.");

        // M
        wordHints.put("mango", "A fleshy, oval, yellowish-red tropical fruit that is eaten ripe or used green for pickles or chutneys.");
        wordHints.put("mirth", "Amusement, especially as expressed in laughter.");
        wordHints.put("mould", "A fungus that grows in the form of multicellular filaments called hyphae.");
        wordHints.put("mural", "A painting or other work of art executed directly on a wall.");
        wordHints.put("musky", "Having a smell or taste of musk, or similar to that of musk.");
        wordHints.put("mocha", "A strong coffee made from Arabian beans, or a flavoring that combines coffee and chocolate.");
        wordHints.put("mossy", "Covered in or resembling moss.");
        wordHints.put("motel", "A roadside hotel designed primarily for motorists, typically having the rooms arranged in a low building with parking directly outside.");
        wordHints.put("mover", "A company that transports furniture and other possessions in a moving van.");
        wordHints.put("mulch", "A material spread around or over a plant to enrich or insulate the soil.");
        wordHints.put("mummy", "A body of a human being or animal that has been ceremonially preserved by removal of the internal organs, treatment with natron and resins, and wrapping in bandages.");
        wordHints.put("munch", "Eat (something) with a continuous and often audible action of the jaws.");
        wordHints.put("muzzy", "Unable to think clearly; fuzzy.");
        wordHints.put("maxim", "A short, pithy statement expressing a general truth or rule of conduct.");
        wordHints.put("mince", "Cut up or grind (food, especially meat) into very small pieces, typically in a machine with revolving blades.");
        // N
        wordHints.put("noble", "Belonging to a hereditary class with high social or political status; aristocratic.");
        wordHints.put("notch", "An indentation or incision on an edge or surface.");
        wordHints.put("nylon", "A tough, lightweight, elastic synthetic polymer with a protein-like chemical structure.");
        wordHints.put("nymph", "A mythological spirit of nature imagined as a beautiful maiden inhabiting rivers, woods, or other locations.");
        wordHints.put("nadir", "The lowest point in the fortunes of a person or organization.");
        wordHints.put("nasal", "Relating to the nose.");
        wordHints.put("natty", "Smart and fashionable.");
        wordHints.put("naval", "Relating to a navy or navies.");
        wordHints.put("needy", "Lacking the necessities of life; very poor.");
        wordHints.put("neigh", "The characteristic high-pitched sound made by a horse.");
        wordHints.put("nerve", "A bundle of fibers that transmits impulses of sensation to the brain or spinal cord.");
        wordHints.put("newly", "Recently; not long ago.");
        wordHints.put("ninja", "A person skilled in ninjutsu.");
        wordHints.put("nudge", "Prod (someone) gently, typically with one's elbow, in order to draw their attention to something.");
        wordHints.put("nonce", "The one or single occasion; the present, or immediate, occasion or purpose.");

        // O
        wordHints.put("ocean", "A very large expanse of sea, in particular, each of the main areas into which the sea is divided geographically.");
        wordHints.put("opera", "A dramatic work in one or more acts, set to music for singers and instrumentalists.");
        wordHints.put("orbit", "The curved path of a celestial object or spacecraft around a star, planet, or moon.");
        wordHints.put("oxide", "A binary compound of oxygen with another element or group.");
        wordHints.put("owner", "A person who owns something.");
        wordHints.put("ocher", "An earthy pigment containing ferric oxide, typically with clay, varying from light yellow to brown or red.");
        wordHints.put("oddly", "In a way that is different from what is usual or expected.");
        wordHints.put("offer", "Present or proffer (something) for (someone) to accept or reject as desired.");
        wordHints.put("often", "Frequently; many times.");
        wordHints.put("olden", "Belonging to the time long past.");
        wordHints.put("olive", "A small oval fruit with a hard pit and bitter flesh, green when unripe and black when ripe.");
        wordHints.put("ombre", "Having colors or tones that shade into each other —used especially of fabrics in which the color is graduated from light to dark.");
        wordHints.put("onset", "The beginning of something, especially something unpleasant.");
        wordHints.put("otter", "An aquatic or marine carnivorous mammal with a streamlined body, webbed feet, and a long tapering tail.");
        wordHints.put("ounce", "A unit of weight of one sixteenth of a pound avoirdupois (approximately 28 grams).");
        // P
        wordHints.put("piano", "A large keyboard musical instrument with a wooden case enclosing a soundboard and metal strings.");
        wordHints.put("pluck", "To pull something, especially with a sudden movement, to remove it.");
        wordHints.put("plump", "Having a full rounded shape.");
        wordHints.put("poise", "Graceful and elegant bearing in a person.");
        wordHints.put("porch", "A covered shelter projecting in front of the entrance of a building.");
        wordHints.put("proud", "Feeling deep pleasure or satisfaction as a result of one's own achievements.");
        wordHints.put("prune", "A dried plum, especially from certain varieties.");
        wordHints.put("pulse", "A rhythmical throbbing of the arteries as blood is propelled through them.");
        wordHints.put("punch", "To strike with a fist.");
        wordHints.put("pupal", "Relating to the stage in the life cycle of an insect between larva and adult.");
        wordHints.put("puree", "A smooth cream of liquidized or crushed fruit or vegetables.");
        wordHints.put("pique", "Stimulate (interest or curiosity).");
        wordHints.put("peach", "A round stone fruit with juicy yellow flesh and downy pinkish-yellow skin.");
        wordHints.put("pilaf", "A dish in which rice is cooked in a seasoned broth.");
        wordHints.put("pivot", "The central point, pin, or shaft on which a mechanism turns or oscillates.");

        // Q
        wordHints.put("quota", "A limited quantity of a particular product that under official controls can be produced, exported, or imported.");
        wordHints.put("quark", "A type of very small particle that is a fundamental constituent of matter.");
        wordHints.put("queue", "A line or sequence of people or vehicles awaiting their turn to be attended to or to proceed.");
        wordHints.put("quilt", "A warm bed covering made of padding enclosed between layers of fabric and kept in place by lines of stitching.");
        wordHints.put("quite", "To the utmost or most absolute extent or degree; absolutely; completely.");
        wordHints.put("quack", "The characteristic sound made by a duck or a fraudulent or ignorant pretender to medical skill.");
        wordHints.put("quick", "Moving fast or doing something in a short time.");
        wordHints.put("quirk", "A peculiar behavioral habit.");
        wordHints.put("queen", "The female ruler of an independent state, especially one who inherits the position by right of birth.");
        wordHints.put("quail", "A small, short-tailed old-world game bird resembling a small partridge, typically having brown camouflaged plumage.");
        wordHints.put("quest", "A long or arduous search for something.");
        wordHints.put("quill", "A long, stiff feather of a bird or a pen made from such a feather.");
        wordHints.put("query", "A question, especially one addressed to an official or organization.");
        wordHints.put("quell", "Put an end to (a rebellion or other disorder), typically by the use of force.");
        wordHints.put("quash", "Reject or void, especially by legal procedure.");

        // R
        wordHints.put("radar", "A system for detecting the presence, direction, distance, and speed of aircraft, ships, and other objects, by sending out pulses of high-frequency electromagnetic waves that are reflected off the object back to the source.");
        wordHints.put("radio", "The transmission and reception of electromagnetic waves of radio frequency, especially those carrying sound messages.");
        wordHints.put("razor", "A device used for cutting hair close to the skin.");
        wordHints.put("rally", "A mass meeting of people making a political protest or showing support for a cause.");
        wordHints.put("ranch", "A large farm, especially in the western US and Canada, where cattle or other animals are bred and raised.");
        wordHints.put("rapid", "Happening in a short time or at a great rate.");
        wordHints.put("ravel", "Untangle or unravel something.");
        wordHints.put("raven", "A large heavily built crow with mainly black plumage, feeding chiefly on carrion.");
        wordHints.put("ready", "In a suitable state for an activity, action, or situation; fully prepared.");
        wordHints.put("rebel", "A person who rises in opposition or armed resistance against an established government or ruler.");
        wordHints.put("ridge", "A long narrow hilltop, mountain range, or watershed.");
        wordHints.put("rifle", "A gun, especially one fired from shoulder level, having a long spirally grooved barrel intended to make a bullet spin and thereby have greater accuracy over a long distance.");
        wordHints.put("rigid", "Unable to bend or be forced out of shape; not flexible.");
        wordHints.put("rider", "A person who is riding or who can ride something, especially a horse, bicycle, motorcycle, or snowboard.");
        wordHints.put("rumba", "A rhythmic dance with Spanish and African elements, originating in Cuba.");

        // S
        wordHints.put("scale", "A set of numbers, amounts, etc., used to measure or compare the level of something.");
        wordHints.put("scarf", "A piece of fabric worn around the neck or head for warmth, sun protection, cleanliness, fashion, or religious reasons.");
        wordHints.put("scary", "Frightening; causing fear.");
        wordHints.put("scene", "The place where an incident in real life or fiction occurs or occurred.");
        wordHints.put("scope", "The extent of the area or subject matter that something deals with or to which it is relevant.");
        wordHints.put("score", "The number of points, goals, runs, etc., achieved in a game or by a team or an individual.");
        wordHints.put("screw", "A short, slender, sharp-pointed metal pin with a raised helical thread running around it and a slotted head, used to join things together by being rotated so that it pierces wood or other material and is held tightly in place.");
        wordHints.put("sheep", "A domesticated ruminant animal with a thick woolly coat and (typically only in the male) curving horns.");
        wordHints.put("shelf", "A flat length of wood or other hard material, attached to a wall or forming part of a piece of furniture, that provides a surface for the storage or display of objects.");
        wordHints.put("shine", "Give out a bright light.");
        wordHints.put("shirt", "A garment for the upper body made of cotton or a similar fabric, with a collar and sleeves, and with buttons down the front.");
        wordHints.put("shock", "A sudden upsetting or surprising event or experience.");
        wordHints.put("short", "Measuring a small distance from end to end.");
        wordHints.put("shout", "A loud cry to convey a strong emotion or to call to someone.");
        wordHints.put("sight", "The faculty or power of seeing.");

        // T
        wordHints.put("table", "A piece of furniture with a flat top and one or more legs, providing a level surface for eating, writing, or working.");
        wordHints.put("tango", "A ballroom dance originating in Buenos Aires, characterized by marked rhythms and postures and abrupt pauses.");
        wordHints.put("taste", "The sensation of flavor perceived in the mouth and throat on contact with a substance.");
        wordHints.put("teach", "Impart knowledge to or instruct (someone) as to how to do something.");
        wordHints.put("tears", "Drops of clear salty liquid secreted by glands in your eyes.");
        wordHints.put("tempt", "Entice or try to entice (someone) to do or acquire something that they find attractive but know to be wrong or unwise.");
        wordHints.put("thank", "Express gratitude to (someone), especially by saying 'Thank you'.");
        wordHints.put("think", "Have a particular opinion, belief, or idea about someone or something.");
        wordHints.put("thorn", "A stiff, sharp-pointed woody projection on the stem or other part of a plant.");
        wordHints.put("three", "Equivalent to the sum of one and two; one more than two; 3.");
        wordHints.put("tiger", "A large cat species native to the forests of Asia, having a tawny coat with black stripes.");
        wordHints.put("title", "The name of a book, composition, or other artistic work.");
        wordHints.put("torch", "A portable means of illumination such as a piece of wood or cloth soaked in tallow or an oil lamp on a pole, sometimes carried ceremonially.");
        wordHints.put("touch", "Come into or be in contact with.");
        wordHints.put("trace", "Find or discover by investigation.");

        // U
        wordHints.put("umbra", "The fully shaded inner region of a shadow cast by an opaque object.");
        wordHints.put("unity", "The state of being united or joined as a whole.");
        wordHints.put("upper", "Situated above another part.");
        wordHints.put("usage", "The action of using something or the fact of being used.");
        wordHints.put("usual", "Habitually or typically occurring or done; customary.");
        wordHints.put("ultra", "Going beyond what is usual or ordinary; excessive; extreme.");
        wordHints.put("under", "Extending or directly beneath something else.");
        wordHints.put("unite", "Come or bring together for a common purpose or action.");
        wordHints.put("usher", "A person who shows people to their seats, especially in a theater or at a wedding.");
        wordHints.put("until", "Up to the time that or when; till.");
        wordHints.put("urban", "In, relating to, or characteristic of a city or town.");
        wordHints.put("upset", "Make (someone) unhappy, disappointed, or worried.");
        wordHints.put("usage", "The action of using something or the fact of being used.");
        wordHints.put("usurp", "Take (a position of power or importance) illegally or by force.");

        // V
        wordHints.put("value", "The importance, worth, or usefulness of something.");
        wordHints.put("vapor", "A substance diffused or suspended in the air, especially one normally liquid or solid.");
        wordHints.put("vocal", "Relating to the human voice.");
        wordHints.put("vivid", "Producing powerful feelings or strong, clear images in the mind.");
        wordHints.put("vital", "Absolutely necessary or important; essential.");
        wordHints.put("voice", "The sound produced in a person's larynx and uttered through the mouth, as speech or song.");
        wordHints.put("vouch", "Assert or confirm as a result of one's own experience that something is true or accurately so described.");
        wordHints.put("visit", "Go to see and spend time with (someone) socially.");
        wordHints.put("vibes", "A person's emotional state or the atmosphere of a place as communicated to and felt by others.");
        wordHints.put("vague", "Of uncertain, indefinite, or unclear character or meaning.");
        wordHints.put("venue", "The place where something happens, especially an organized event such as a concert, conference, or sports event.");
        wordHints.put("vista", "A pleasing view, especially one seen through a long, narrow opening.");
        wordHints.put("vault", "A large room or chamber used for storage, especially an underground one.");
        wordHints.put("vigor", "Physical strength and good health.");
        wordHints.put("vexed", "Annoyed, frustrated, or worried.");

        // W
        wordHints.put("watch", "Look at or observe attentively over a period of time.");
        wordHints.put("waltz", "A dance performed in triple time, typically by one couple, and characterized by a graceful rise and fall motion.");
        wordHints.put("waste", "Use or expend carelessly, extravagantly, or to no purpose.");
        wordHints.put("water", "A colorless, transparent, odorless liquid that forms the seas, lakes, rivers, and rain and is the basis of the fluids of living organisms.");
        wordHints.put("weave", "Form (fabric or a fabric item) by interlacing long threads passing in one direction with others at a right angle to them.");
        wordHints.put("weird", "Suggesting something supernatural; uncanny.");
        wordHints.put("widow", "A woman who has lost her spouse by death and has not married again.");
        wordHints.put("witty", "Showing or characterized by quick and inventive verbal humor.");
        wordHints.put("woken", "Rouse from sleep; cause to stop sleeping.");
        wordHints.put("woven", "Made by weaving, used especially of fabric.");
        wordHints.put("wrist", "The joint connecting the hand with the forearm.");
        wordHints.put("write", "Mark (letters, words, or other symbols) on a surface, typically paper, with a pen, pencil, or similar implement.");
        wordHints.put("wrong", "Not correct or true; incorrect.");
        wordHints.put("wrink", "A small line or fold in something, especially fabric or the skin.");
        wordHints.put("wreck", "The destruction or sinking of a ship, or a ship that has been destroyed or sunk.");

        // X
        wordHints.put("xenon", "A colorless, dense, odorless noble gas used in lamps and medical imaging.");
        wordHints.put("xerox", "A process of photocopying (named after the company) using a particular type of copier.");
        wordHints.put("xylem", "The vascular tissue in plants that conducts water and dissolved nutrients upward from the root.");
        wordHints.put("xebec", "A small three-masted sailing ship with a long overhanging bow and stern.");
        wordHints.put("xeric", "Characterized by or adapted to an extremely dry habitat.");

        // Y
        wordHints.put("yacht", "A medium-sized sailboat equipped for cruising or racing.");
        wordHints.put("yield", "Produce or provide (a natural, agricultural, or industrial product).");
        wordHints.put("yodel", "To sing with rapid changes of pitch between the normal voice and falsetto.");
        wordHints.put("young", "Having lived or existed for only a short time.");
        wordHints.put("yucca", "A plant with hard, pointed leaves and clusters of white flowers, native to the hot and dry parts of North and Central America.");
        wordHints.put("youth", "The period between childhood and adult age.");
        wordHints.put("yummy", "Highly attractive or pleasing, especially to the senses of taste and smell.");
        wordHints.put("yeast", "A microscopic fungus used in baking and brewing because of its ability to ferment.");
        wordHints.put("years", "The time taken by a planet to make one revolution around the sun.");
        wordHints.put("yogic", "Relating to or characteristic of yoga.");
        wordHints.put("yokel", "An unsophisticated country person.");
        wordHints.put("yokan", "A sweet jelly dessert from Japan made from red bean paste, agar, and sugar.");
        wordHints.put("yonic", "Symbolic of femininity and reproductive power, particularly the vulva.");

        // Z
        wordHints.put("zebra", "An African wild horse with black-and-white stripes and an erect mane.");
        wordHints.put("zesty", "Having a strong, pleasant, and somewhat spicy flavor or smell.");
        wordHints.put("zonal", "Relating to or affecting a particular zone or area.");
        wordHints.put("zoned", "Having been divided into zones, as for different land uses.");
        wordHints.put("zippo", "A brand of refillable, metal lighter known for its reliability and windproof capability.");
        wordHints.put("zincs", "A bluish-white metal that is used to galvanize iron and make alloys and batteries.");
        wordHints.put("zooks", "An exclamation of surprise or concern, often used in humorous context.");
        wordHints.put("zeros", "The figure or symbol 0, which in the Arabic numeral system represents the absence of quantity.");
        wordHints.put("zills", "Small metallic cymbals worn on the fingers and thumbs of belly dancers.");
        wordHints.put("zonal", "Of or relating to a zone or zones.");
        wordHints.put("zesty", "Pleasingly piquant or spicy.");
        wordHints.put("zebra", "An African wild horse with distinctive black-and-white striped fur.");
        wordHints.put("zippy", "Bright, fresh, or lively.");
        wordHints.put("zooms", "Move or travel very quickly.");
    }

     // Retrieves a hint for a given word, returns a default message if no hint is available
    public String getHintForWord(String word) {
        return wordHints.getOrDefault(word.toLowerCase(), "No hint available.");
    }

    // Validates if a word is in the word bank
    public boolean isValidWord(String word) {
        char firstLetter = Character.toLowerCase(word.charAt(0));
        Set<String> words = wordsByLetter.get(firstLetter);
        return words != null && words.contains(word.toLowerCase());
    }
    
     // Retrieves a set of words that start with a given letter
    public Set<String> getWordsStartingWith(char letter) {
        return wordsByLetter.get(Character.toLowerCase(letter)); 
        // Return the set of words for the letter
    }
}