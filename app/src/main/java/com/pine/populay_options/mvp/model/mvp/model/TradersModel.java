package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.TradersEntity;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;

import java.util.List;

import javax.inject.Inject;
@ActivityScope
public class TradersModel extends BaseModel implements TradersContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    List<TradersEntity> mTradersEntitys;
    @Inject
    public TradersModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void initData() {
        TradersEntity mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("If you live in the modern world, you have heard the name George Soros many times before. You must have heard some conspiracy theories regarding the man as well since, at least to some, he is the most suspicious man who has ever lived. It is not surprising for someone who is known as the man who broke the bank of England to be a little suspicious in the eyes of the unknowing. We will talk about how he came upon that name a little later. First, let us talk about who he is and where he came from and why he is one of the best Forex traders to follow and keep your eye on.\n" +
                "\n" +
                "George Soros’s birth name was Gyorgy Schwartz. The name was changed sometimes in the 1930s by his father, as he didn’t want the Nazi Germans to single them out and hunt them down as they were doing during those dark times in Nazi Germany. The man was just a boy at the time, so the name stuck for the rest of his life. The family moved to Switzerland sometime in 1946 and then to London in 1947. George would go to the London School of Economics here, graduating in 1952 with a degree in philosophy. After this, he went on to work with the bank known as Singer & Friedlander. He would spend many years working for many different banks and financial establishments throughout those years. Many successful Forex traders’ stories are similar to this one: from nothing to everything.\n" +
                "\n" +
                "Then, in 1969 he founded his own fund management firm, the Soros Fund Management, concentrating on managing hedge funds, which has up to date produced $40 billion in revenue since foundation. Then, in the 1970s, he found the Quantum Fund, through which he started trading on the foreign exchange market. For many years he kept selling, buying, learning, recording until the fateful day in 1992 when he broke the English bank. He realized, after many years of observation, that he could do something that would make him a whole lot of money. He took 10 billion British Pound Sterlings and short sold them. With this action he was able to make about One billion GBP Sterling in a single day, making him the man who made the most money in one day, and also bringing him fame as the best Forex trader in the world. So far.\n" +
                "\n" +
                "Another consequence of his actions was that the GBP had to leave the European Foreign Exchange because the value of the Sterling fell far below the agreed-upon value. Which is why he is called the man who broke the bank of England. He made the bank withdraw the currency from the market. That is a pretty big thing to do.");
        mTradersEntity.setTradersName("George Soros");
        mTradersEntity.setImage(R.mipmap.ic_traders);
        mTradersEntitys.add(mTradersEntity);

        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("When growing up, he seemed like a regular kid. Loved his sweets, enjoyed his cereal and had fun playing outside until dusk. Then he grew up and went to the Wharton School of Business, which is part of the University of Pennsylvania, which put him on the path to becoming one of the most famous and one of the best Forex traders in the world. The journey there for Krieger was as long as it was for the person we discussed previously, and yet it was no less wrought with issues.\n" +
                "\n" +
                "After graduating from school he joined the Salomon Brothers and then moved to the Bankers Trust company in rapid succession. While working at the Bankers Trust company, his reputation as a good businessman with a strong financial acumen started growing fast. After a short period of time, the company gave Krieger a boost from the standard $50 million trading capital limit to a staggering $700 million. This type of capital allowed him to do something that he would not have been able to achieve with his previous capital when October 19th eventually rolled around.\n" +
                "\n" +
                "The world was in a panic, the financial markets were in trouble, so was the foreign exchange market. Looking closely at the market, Krieger realizes that the New Zealand Dollar is overvalued. So he takes his capital, combines it with a 400:1 leverage and starts short selling the NZD. After a while of doing this, his earnings for the Bankers Trust come to about 300 million dollars, and he is done, leaning back against his chair and smiling quaintly at his earnings. Though, a year later, he leaves the firm because he believes that 3 million dollars are not an appropriate bonus for his earnings of 300 million for the company. Yet, his reputation as one of the best Forex traders on the market persists.");
        mTradersEntity.setTradersName("Andrew Krieger");
        mTradersEntity.setImage(R.mipmap.ic_traders1);
        mTradersEntitys.add(mTradersEntity);

        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("Bruce Kovner is a humble man, whose origin story is humble as well. He was born in New York, in Brooklyn and didn’t do anything outstanding in terms of financial success until he was thirty-two. He took the $3000 he had on his credit card and bought some Soybean futures. This brought him some nice winnings of $23000, even though he could have had $40 000 if he had known when to pull out. This is when he learned about appropriate risk management and how to manage your funds well. A lesson every one of the best Forex traders had to learn at one point or another.\n" +
                "\n" +
                "He would spend the next few years working Commodities Corporation until the foundation of his own company, Caxton Corporation and later Caxton Associates, which concentrated on Foreign Exchange and other asset diversifying operations. At its peak, the Caxton Corporation was making around $14 Billion, which is what brought the man the fame of one of the best Forex traders in the world.");
        mTradersEntity.setTradersName("Bruce Kovner");
        mTradersEntity.setImage(R.mipmap.ic_traders2);
        mTradersEntitys.add(mTradersEntity);

        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("There isn’t much more to say about Paul Tudor Jones other than what has already been showcased in the film about him, aptly named the Trader. This man’s beginnings were as humble as any other’s and yet he too was able to create a reputation of one the top Forex traders in the world for himself. The way he did it was pretty simple – when the legendary black Monday rolled around, he bet against a very large currency with a whole lot of capital. He short-sold the Yen pretty hard and created a 15% return for his company, which earned him not only a whole lot of money but the reputation of a financier you want on your side.");
        mTradersEntity.setTradersName("Paul Tudor Jones");
        mTradersEntity.setImage(R.mipmap.ic_traders6);
        mTradersEntitys.add(mTradersEntity);

        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("A man who deserves to be much better known than many on this list. Urs Schwarzenbach, despite being the least well-known trader, is also sitting extremely close to being the most successful Forex trader, purely through his own ability and knowledge.  His history is fascinating – he was born and raised in Switzerland. He worked at the Swiss Bank Corporation, as part of the foreign affairs division at first and then as part of the foreign exchange market division. Because of his success, he was sent to London not long after. His father gave his son some money as something to bring on his trip, despite not having much to himself. One hundred thousand Swiss Francs, at twenty-four years old, was used by Urs to trade on the Foreign Exchange market and produce his first million. He would keep trading on the market privately, earning himself millions of dollars until he was able to start his own company.\n" +
                "\n" +
                "Today the man trades with his own money. He says this gives him the ability to take risks that he would not be able to take with other peoples’ money. And he is known for taking larger risks than more conservative companies do. Though at the same time, he is known to be a careful trader, one that knows how to manage his risks. Which is why his capital is growing by the day. His success has brought him the reputation of being one of the top Forex traders in the world, despite not being publically known. He prefers it this way.");
        mTradersEntity.setTradersName("Urs Schwarzenbach");
        mTradersEntity.setImage(R.mipmap.ic_traders5);
        mTradersEntitys.add(mTradersEntity);


        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("A man born to a rather regular, middle-class family, without any outstanding features or fortunes, who would eventually become one of the best FX traders in the world by playing on the market with the German Mark. Some of you might not remember the time when the German mark was a thing, but it was a big thing before the Euro. During the 1980s the currency was getting devalued daily, because of the political issues surrounding the Berlin wall and its destruction. Taking a close look at the currency Stanley saw that the currency was severely undervalued, so he opened a position of several million dollars, buying the currency at a higher price than what was on the market. Soros made him increase the position several times a little later. The result was a 60% return on the investment and the first Billion dollars that the man would make for his employer. The kind of fame and fortune that we all would like in our lives.");
        mTradersEntity.setTradersName("Stanley Druckenmiller");
        mTradersEntity.setImage(R.mipmap.ic_traders4);
        mTradersEntitys.add(mTradersEntity);


        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("Not all traders started out going to Business schools or economics schools. This Farmingdale, New York boy went to a Cornell University for the  Architectural and Design Fine arts degree. Though at the same time he was earning his MBA in Finance from a Johnson School of Management from the same university. So maybe it’s not entirely true that he didn’t go to business school. The thing is, he spent many years going about investment on his own. At one point he turned $12 000 into $250 000 and lost it all on the market. The result was an important lesson – manage your risks.\n" +
                "\n" +
                "Soon he joined the Salomon Brothers investment company, where he, in a short time, became part of the new Foreign Exchange department. In this position, he started earning the company three hundred million dollars a year, until the 1990s when he left the company. All of this has earned him the reputation of one of the best Forex traders in the world. A reputation well earned, especially if you keep making three hundred million dollars for the company you work at, every year, for half a decade or so.");
        mTradersEntity.setTradersName("Bill Lipschutz");
        mTradersEntity.setImage(R.mipmap.ic_traders3);
        mTradersEntitys.add(mTradersEntity);


        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("The man was once one of the largest foreign exchange market traders in the world, and we do not mean physically. At one point in the 1980s, this legendary trader held positions on the German Mark in the amounts of three hundred million dollars. The dollar was strong then, so he had nothing to fear. He made loads of money in those years but then realized that his position as the top foreign exchange trader for the commodities corporation required him to be at work twenty-four hours a day, seven days a week. This led to a divorce and many years of unhappiness for him. So he quit the foreign exchange market, also believing that the Forex market today is not about trade but more about guessing the central banks’ positions on currencies. Still, despite not being in the industry anymore, he remains one of the best Forex traders to have ever graced the market with their capital. Maybe he has a point, or maybe he made enough money to not want to work anymore. Either way, he is doing great.");
        mTradersEntity.setTradersName("Michael Marcus");
        mTradersEntity.setImage(R.mipmap.ic_traders7);
        mTradersEntitys.add(mTradersEntity);


        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("This British man had to abandon his studies at fifteen so he could help his family business. Working in catering for a while, he took over his father’s business and helped it grow until he was able to sell it for a nice bit of profit. Using the money he had earned, he moved to the Bahamas and started trading from there, while paying very low taxes. He spent years growing his capital privately until he was able to match the George Soros gamble against the GBP in the 1990s. How much money he made off of this gamble is not specifically known, but what is known is that they do not top the earnings he got on his short sale of the Mexican Peso. Either way, the man has made tons of money in the Forex industry, simply through his persistence and understanding of the market, which is what made him into one of the most successful Forex traders who operate today.");
        mTradersEntity.setTradersName("Joe Lewis");
        mTradersEntity.setImage(R.mipmap.ic_traders8);
        mTradersEntitys.add(mTradersEntity);


        mTradersEntity=new TradersEntity();
        mTradersEntity.setArticle("Michael Steinhardt is a bit of a legendary figure in the investment world, holding his record of a 24% continuous compound growth of 28 years unchallenged anywhere in the world’s markets. The man started out on the stock market, receiving his first investment capital in the shape of envelopes full of money to invest in the market. After growing his capital he started diversifying his portfolio, eventually starting to work in the Foreign Exchange markets, with the same success as with everything else. This is not only impressive but also what makes him one of the most successful traders in Forex in the world.");
        mTradersEntity.setTradersName("Michael Steinhardt");
        mTradersEntity.setImage(R.mipmap.ic_traders9);
        mTradersEntitys.add(mTradersEntity);
    }
}