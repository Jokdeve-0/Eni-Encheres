/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.TEST;

import java.sql.Date;
import java.time.LocalDate;

import fr.reddev.encheres.BO.Article;
import fr.reddev.encheres.BO.Auction;
import fr.reddev.encheres.BO.Category;
import fr.reddev.encheres.BO.User;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class Mocks {
    public static User user = new User("alias", "lastname", "firstname", "email", "9999999999", "street", "99999", "city", "09fc96082d34c2dfc1295d92073b5ea1dc8ef8da95f14dfded011ffb96d3e54b", 1000, true, true); 
    public static User userModify = new User(15,"jokmocks", "mocks", "jok", "email@addictocode.fr", "9999999999", "route du web", "99999", "joktown", "09fc96082d34c2dfc1295d92073b5ea1dc8ef8da95f14dfded011ffb96d3e54b", 1000, true, true); 
    public static Article article = new Article(null, "article", "description", Date.valueOf("2022-01-01"),  Date.valueOf("2022-12-01"), 75, null, 1, 1, "NS", "seller");
    public static Article articleModify = new Article(null, "articlemock", "descriptionmock", Date.valueOf("2022-12-01"),  Date.valueOf("2022-01-01"), 75, null, 1, 1, "NS", "sellermock");
    public static Category category = new Category("category");
    public static Category categoryModify = new Category(null,"categorymock");
    public static Auction auction = new Auction(null,null,Date.valueOf(LocalDate.now()),1,"categorymock");
    public static Auction auctionModify = new Auction(null,null,Date.valueOf(LocalDate.now()),1,"categorymock");
}
