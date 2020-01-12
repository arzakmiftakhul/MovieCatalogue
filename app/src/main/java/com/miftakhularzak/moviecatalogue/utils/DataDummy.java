package com.miftakhularzak.moviecatalogue.utils;

import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.MovieResponse;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.TvShowResponse;

import java.util.ArrayList;

public class DataDummy {
    public static ArrayList<MovieEntity> generateMovieDummy(){
        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity(501169,
                "Motherboard",
                "Prim and proper mother, Lesley fights hard to resist her addiction to technology whilst battling motherhood and keeping with in the social norms.",
                "2016-10-19",
                "/f0HElZCWDyAz9vnDG64UHKzTWVn.jpg",
                "Fantasy, Science Fiction",
                "VFS Productions",
                "English",
                "0",
                "11"));
        movies.add(new MovieEntity(419704,
                "Ad Astra",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "2019-09-17",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                "Science Fiction, Drama, Adventure",
                "New Regency Produtions, Keep Your Head",
                "English",
                "6.1",
                "123"));
        movies.add(new MovieEntity(330457,
                "Frozen II",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "2019-11-20",
                "/pjeMs3yqRmFL3giJy4PMXWZTTPa.jpg",
                "Animation, Family, Music",
                "Walt Disney Animation Studios, Walt Disney Pictures",
                "English",
                "7",
                "104"));
        movies.add(new MovieEntity(492188,
                "Marriage Story",
                "A stage director and an actor struggle through a grueling, coast-to-coast divorce that pushes them to their personal extremes.",
                "2019-11-06",
                "/bm6zKJjKYKrIy3dcnOLk0kF85cl.jpg",
                "Drama",
                "Heyday Films",
                "English",
                "8.2",
                "137"));
        movies.add(new MovieEntity(449924,
                "Ip Man 4: The Finale",
                "Ip Man 4 is an upcoming Hong Kong biographical martial arts film directed by Wilson Yip and produced by Raymond Wong. It is the fourth in the Ip Man film series based on the life of the Wing Chun grandmaster of the same name and features Donnie Yen reprising the role. The film began production in April 2018 and ended in July the same year.",
                "2019-12-20",
                "/vn94LlNrbUWIZZyAdmvUepFBeaY.jpg",
                "Action,Drama, History",
                "Pegasus Motion Pictures",
                "English, 广州话 / 廣州話, 普通话",
                "7",
                "105"));
        movies.add(new MovieEntity(181812,
                "Star Wars: The Rise of Skywalker",
                "The next installment in the franchise and the conclusion of the “Star Wars“ sequel trilogy as well as the “Skywalker Saga.”",
                "2019-12-18",
                "/db32LaOibwEliAmSL2jjDF6oDdj.jpg",
                "Action, Adventure, Science Fiction",
                "Lucas Film, Bad Robot, Walt Disney Pictures",
                "English",
                "5.8",
                "141"));
        movies.add(new MovieEntity(398978,
                "The Irishman",
                "Pennsylvania, 1956. Frank Sheeran, a war veteran of Irish origin who works as a truck driver, accidentally meets mobster Russell Bufalino. Once Frank becomes his trusted man, Bufalino sends him to Chicago with the task of helping Jimmy Hoffa, a powerful union leader related to organized crime, with whom Frank will maintain a close friendship for nearly twenty years.",
                "2019-11-01",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Crime, History, Drama",
                "Tribeca Productions, Sikelia Productions",
                "English",
                "8",
                "209"));
        movies.add(new MovieEntity(509967,
                "6 Underground",
                "After faking his death, a tech billionaire recruits a team of international operatives for a bold and bloody mission to take down a brutal dictator.",
                "2019-12-13",
                "/lnWkyG3LLgbbrIEeyl5mK5VRFe4.jpg",
                "Action, Adventure",
                "Platinum Dunes, Skydance Media",
                "English",
                "7",
                "127"));
        movies.add(new MovieEntity(546554,
                "Knives Out",
                "When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.",
                "2019-11-27",
                "/pThyQovXQrw2m0s9x82twj48Jq4.jpg",
                "Mystery, Comedy, Thriller",
                "Liongate, FilmNation Entertainment",
                "English",
                "7.9",
                "130"));
        movies.add(new MovieEntity(431580,
                "Abominable",
                "A group of misfits encounter a young Yeti named Everest, and they set off to reunite the magical creature with his family on the mountain of his namesake.",
                "2019-09-19",
                "/20djTLqppfBx5WYA67Y300S6aPD.jpg",
                "Animation, Adventure, Comedy, Family",
                "DreamWorks Animation, Pearl Studio",
                "English",
                "6.8",
                "92"));
        return movies;
    }

    public static MovieEntity getMovie(int movieId){
        for(int i=0; i<generateMovieDummy().size(); i++){
            MovieEntity model = generateMovieDummy().get(i);
            if(model.getMovieId()==movieId) return model;
        }
        return null;
    }

    public static ArrayList<TvShowEntity>generateTvShowDummy(){
        ArrayList<TvShowEntity> tvShows = new ArrayList<>();
        tvShows.add(new TvShowEntity(1417,
                "Glee",
                "In this musical comedy, optimistic high school teacher Will Schuester tries to refuel his own passion while reinventing the high school's glee club and challenging a group of outcasts to realize their star potential as they strive to outshine their singing competition while navigating the cruel halls of McKinley High.",
                "/62T2vsZYTje3KhdCsiLR1K1RFJW.jpg",
                "Comedy, Drama",
                "20th Century Fox Television, Ryan Murphy Productions, Brad Falchuk Teley-vision",
                "English",
                "6.2",
                "2009-05-19"));
        tvShows.add(new TvShowEntity(82856,
                "The Mandalorian",
                "The Mandalorian joins a crew of mercenaries on a dangerous mission.",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Sci-Fi & Fantasy, Action & Adventure",
                "Lucasfilm, Walt Disney Studios",
                "English",
                "7.8",
                "2019-12-29"));
        tvShows.add(new TvShowEntity(60625,
                "Rick and Morty",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "Animation, Comedy, Sci-Fi & Fantasy",
                "William Street, Harmonius Claptrap",
                "English",
                "8.6",
                "2019-12-08"));
        tvShows.add(new TvShowEntity(60735,
                "Th Flash",
                "Pariah enlists Black Lightning to help stop the Anti-Monitor after Flash-90 shares what he learned from his battle in ‘Elseworlds.’ With the help of Black Lightning, Barry, Cisco and Killer Frost come up with a plan that could save them all. Meanwhile, Iris has a heart-to-heart with Ryan Choi, while Oliver and Diggle return to an old familiar stomping ground.",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "Drama, Sci-Fi & Fantasy",
                "Warner Bros. Television, Beranti Produtions",
                "English",
                "6.7",
                "2018-10-09"));
        tvShows.add(new TvShowEntity(71641,
                "4 Blocks",
                "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple.",
                "/jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
                "Drama",
                "Turner Germany",
                "Dutch",
                "4.5",
                "2019-11-07"));
        tvShows.add(new TvShowEntity(63639,
                "The Expanse",
                "A thriller set two hundred years in the future following the case of a missing young woman who brings a hardened detective and a rogue ship's captain together in a race across the solar system to expose the greatest conspiracy in human history.",
                "/wikmaI7OVqmq2O9HfknkzxX6Ygu.jpg",
                "Drama, Mystery",
                "Syfy, Legendary Television",
                "English",
                "7.6",
                "2017-02-01"));
        tvShows.add(new TvShowEntity(1622,
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "Drama, Mystery",
                "Supernatural Films, Kripke Enterprises",
                "English",
                "7.4",
                "2019-10-10"));
        tvShows.add(new TvShowEntity(68507,
                "His Dark Materials",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                "/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
                "Drama, Sci-Fi & Fantasy",
                "Bad Wolf, New Line Cinema",
                "English",
                "7.6",
                "2019-11-03"));
        tvShows.add(new TvShowEntity(44217,
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "/ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg",
                "Action & Adenture, Drama",
                "Shaw Media, World 2000 Entertainment",
                "English",
                "7.5",
                "2019-12-04"));
        tvShows.add(new TvShowEntity(2734,
                "Law & Order: Special Victims Unit",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                "Crime, Drama",
                "Wolf Films, Universal Television",
                "English",
                "6.5",
                "2019-09-26"));
        tvShows.add(new TvShowEntity(456,
                "Season 10",
                "The tenth season of the animated television series The Simpsons was originally broadcast on the Fox network in the United States between August 23, 1998 and May 16, 1999. It contains twenty-three episodes, starting with \"Lard of the Dance\". The Simpsons is a satire of a middle class American lifestyle epitomized by its family of the same name, which consists of Homer, Marge, Bart, Lisa and Maggie. Set in the fictional city of Springfield, the show lampoons American culture, society, television, and many aspects of the human condition.",
                "/t4wiOGBgoSRgofYxQvRL5WZe6Aj.jpg",
                "Animation, Comedy",
                "The Curiosity Company, Gracia Films",
                "English",
                "7.2",
                "2019-09-29"));
        return tvShows;
    }

    public static TvShowEntity getTvShow(int tvId){
        for(int i=0; i<generateTvShowDummy().size(); i++){
            TvShowEntity tvShow = generateTvShowDummy().get(i);
            if(tvShow.getTvId()==tvId)return tvShow;
        }
        return null;
    }

    public static ArrayList<MovieResponse> generateMovieResponseDummy(){
        ArrayList<MovieResponse> movies = new ArrayList<>();

        movies.add(new MovieResponse(512200,
                "Jumanji: The Next Level",
                "In Jumanji: The Next Level, the gang is back but the game has changed. As they return to rescue one of their own, the players will have to brave parts unknown from arid deserts to snowy mountains, to escape the world's most dangerous game.",
                "2019-12-04",
                "/l4iknLOenijaB85Zyb5SxH1gGz8.jpg",
                "Action, Adventure, Comedy, Fantasy",
                "Columbia Pictures, Seven Bucks Productions",
                "English",
                "6.8",
                "123"));
        movies.add(new MovieResponse(419704,
                "Ad Astra",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
                "2019-09-17",
                "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                "Science Fiction, Drama, Adventure",
                "New Regency Produtions, Keep Your Head",
                "English",
                "6.1",
                "123"));
        movies.add(new MovieResponse(330457,
                "Frozen II",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "2019-11-20",
                "/pjeMs3yqRmFL3giJy4PMXWZTTPa.jpg",
                "Animation, Family, Music",
                "Walt Disney Animation Studios, Walt Disney Pictures",
                "English",
                "7",
                "104"));
        movies.add(new MovieResponse(492188,
                "Marriage Story",
                "A stage director and an actor struggle through a grueling, coast-to-coast divorce that pushes them to their personal extremes.",
                "2019-11-06",
                "/bm6zKJjKYKrIy3dcnOLk0kF85cl.jpg",
                "Drama",
                "Heyday Films",
                "English",
                "8.2",
                "137"));
        movies.add(new MovieResponse(449924,
                "Ip Man 4: The Finale",
                "Ip Man 4 is an upcoming Hong Kong biographical martial arts film directed by Wilson Yip and produced by Raymond Wong. It is the fourth in the Ip Man film series based on the life of the Wing Chun grandmaster of the same name and features Donnie Yen reprising the role. The film began production in April 2018 and ended in July the same year.",
                "2019-12-20",
                "/vn94LlNrbUWIZZyAdmvUepFBeaY.jpg",
                "Action,Drama, History",
                "Pegasus Motion Pictures",
                "English, 广州话 / 廣州話, 普通话",
                "7",
                "105"));
        movies.add(new MovieResponse(181812,
                "Star Wars: The Rise of Skywalker",
                "The next installment in the franchise and the conclusion of the “Star Wars“ sequel trilogy as well as the “Skywalker Saga.”",
                "2019-12-18",
                "/db32LaOibwEliAmSL2jjDF6oDdj.jpg",
                "Action, Adventure, Science Fiction",
                "Lucas Film, Bad Robot, Walt Disney Pictures",
                "English",
                "5.8",
                "141"));
        movies.add(new MovieResponse(398978,
                "The Irishman",
                "Pennsylvania, 1956. Frank Sheeran, a war veteran of Irish origin who works as a truck driver, accidentally meets mobster Russell Bufalino. Once Frank becomes his trusted man, Bufalino sends him to Chicago with the task of helping Jimmy Hoffa, a powerful union leader related to organized crime, with whom Frank will maintain a close friendship for nearly twenty years.",
                "2019-11-01",
                "/mbm8k3GFhXS0ROd9AD1gqYbIFbM.jpg",
                "Crime, History, Drama",
                "Tribeca Productions, Sikelia Productions",
                "English",
                "8",
                "209"));
        movies.add(new MovieResponse(509967,
                "6 Underground",
                "After faking his death, a tech billionaire recruits a team of international operatives for a bold and bloody mission to take down a brutal dictator.",
                "2019-12-13",
                "/lnWkyG3LLgbbrIEeyl5mK5VRFe4.jpg",
                "Action, Adventure",
                "Platinum Dunes, Skydance Media",
                "English",
                "7",
                "127"));
        movies.add(new MovieResponse(546554,
                "Knives Out",
                "When renowned crime novelist Harlan Thrombey is found dead at his estate just after his 85th birthday, the inquisitive and debonair Detective Benoit Blanc is mysteriously enlisted to investigate. From Harlan's dysfunctional family to his devoted staff, Blanc sifts through a web of red herrings and self-serving lies to uncover the truth behind Harlan's untimely death.",
                "2019-11-27",
                "/pThyQovXQrw2m0s9x82twj48Jq4.jpg",
                "Mystery, Comedy, Thriller",
                "Liongate, FilmNation Entertainment",
                "English",
                "7.9",
                "130"));
        movies.add(new MovieResponse(431580,
                "Abominable",
                "A group of misfits encounter a young Yeti named Everest, and they set off to reunite the magical creature with his family on the mountain of his namesake.",
                "2019-09-19",
                "/20djTLqppfBx5WYA67Y300S6aPD.jpg",
                "Animation, Adventure, Comedy, Family",
                "DreamWorks Animation, Pearl Studio",
                "English",
                "6.8",
                "92"));
        return movies;
    }
    public static ArrayList<TvShowResponse>generateTvShowResponseDummy(){
        ArrayList<TvShowResponse> tvShows = new ArrayList<>();
        tvShows.add(new TvShowResponse(1412,
                "Arrow",
                "Upon learning the key to oppose The Monitor, Oliver and team return to Russia on a mission to gather the necessary materials. Connor reunites with a figure from his past.",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Crime, Drama, Mystery",
                "Berlanti Productions, DC Entertaiment",
                "English",
                "5.8",
                "2020-01-14"));
        tvShows.add(new TvShowResponse(82856,
                "The Mandalorian",
                "The Mandalorian joins a crew of mercenaries on a dangerous mission.",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "Sci-Fi & Fantasy, Action & Adventure",
                "Lucasfilm, Walt Disney Studios",
                "English",
                "7.8",
                "2019-12-29"));
        tvShows.add(new TvShowResponse(60625,
                "Rick and Morty",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "Animation, Comedy, Sci-Fi & Fantasy",
                "William Street, Harmonius Claptrap",
                "English",
                "8.6",
                "2019-12-08"));
        tvShows.add(new TvShowResponse(60735,
                "Th Flash",
                "Pariah enlists Black Lightning to help stop the Anti-Monitor after Flash-90 shares what he learned from his battle in ‘Elseworlds.’ With the help of Black Lightning, Barry, Cisco and Killer Frost come up with a plan that could save them all. Meanwhile, Iris has a heart-to-heart with Ryan Choi, while Oliver and Diggle return to an old familiar stomping ground.",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "Drama, Sci-Fi & Fantasy",
                "Warner Bros. Television, Beranti Produtions",
                "English",
                "6.7",
                "2018-10-09"));
        tvShows.add(new TvShowResponse(71641,
                "4 Blocks",
                "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple.",
                "/jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
                "Drama",
                "Turner Germany",
                "Dutch",
                "4.5",
                "2019-11-07"));
        tvShows.add(new TvShowResponse(63639,
                "The Expanse",
                "A thriller set two hundred years in the future following the case of a missing young woman who brings a hardened detective and a rogue ship's captain together in a race across the solar system to expose the greatest conspiracy in human history.",
                "/wikmaI7OVqmq2O9HfknkzxX6Ygu.jpg",
                "Drama, Mystery",
                "Syfy, Legendary Television",
                "English",
                "7.6",
                "2017-02-01"));
        tvShows.add(new TvShowResponse(1622,
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "Drama, Mystery",
                "Supernatural Films, Kripke Enterprises",
                "English",
                "7.4",
                "2019-10-10"));
        tvShows.add(new TvShowResponse(68507,
                "His Dark Materials",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                "/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
                "Drama, Sci-Fi & Fantasy",
                "Bad Wolf, New Line Cinema",
                "English",
                "7.6",
                "2019-11-03"));
        tvShows.add(new TvShowResponse(44217,
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "/ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg",
                "Action & Adenture, Drama",
                "Shaw Media, World 2000 Entertainment",
                "English",
                "7.5",
                "2019-12-04"));
        tvShows.add(new TvShowResponse(2734,
                "Law & Order: Special Victims Unit",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                "Crime, Drama",
                "Wolf Films, Universal Television",
                "English",
                "6.5",
                "2019-09-26"));
        tvShows.add(new TvShowResponse(456,
                "Season 10",
                "The tenth season of the animated television series The Simpsons was originally broadcast on the Fox network in the United States between August 23, 1998 and May 16, 1999. It contains twenty-three episodes, starting with \"Lard of the Dance\". The Simpsons is a satire of a middle class American lifestyle epitomized by its family of the same name, which consists of Homer, Marge, Bart, Lisa and Maggie. Set in the fictional city of Springfield, the show lampoons American culture, society, television, and many aspects of the human condition.",
                "/t4wiOGBgoSRgofYxQvRL5WZe6Aj.jpg",
                "Animation, Comedy",
                "The Curiosity Company, Gracia Films",
                "English",
                "7.2",
                "2019-09-29"));
        return tvShows;
    }
    public static MovieResponse getMovieResponse(int movieId){
        for(int i=0; i<generateMovieResponseDummy().size(); i++){
            MovieResponse model = generateMovieResponseDummy().get(i);
            if(model.getId()==movieId) return model;
        }
        return null;
    }

    public static TvShowResponse getTvShowResponse(int tvShowId){
        for(int i=0; i<generateTvShowResponseDummy().size(); i++){
            TvShowResponse model = generateTvShowResponseDummy().get(i);
            if(model.getId()==tvShowId) return model;
        }
        return null;
    }

}
