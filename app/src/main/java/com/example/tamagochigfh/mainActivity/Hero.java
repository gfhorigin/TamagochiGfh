package com.example.tamagochigfh.mainActivity;

import android.app.Application;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tamagochigfh.DB.HeroDB;
import com.example.tamagochigfh.DB.HeroDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import org.junit.Ignore;

@Entity(tableName = "heroes")
public class Hero {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "hp")
    private float hp = MAX_HP;

    @ColumnInfo(name = "delta_property_point")
    private static final float DELTA_PROPERTY_POINT = 0.1f;

    @ColumnInfo(name = "delta_hp_point")
    private static final float DELTA_HP_POINT = 1.5f;

    @ColumnInfo(name = "max_hp")
    private static final float MAX_HP = 100f;

    // Свойства как отдельные столбцы
    @ColumnInfo(name = "hungry")
    private float hungry = 100f;

    @ColumnInfo(name = "happy")
    private float happy = 100f;

    @ColumnInfo(name = "intelligence")
    private float intelligence = 100f;

    @ColumnInfo(name = "nerdiness")
    private float nerdiness = 100f;

    @ColumnInfo(name = "tiredness")
    private float tiredness = 100f;

    @ColumnInfo(name = "hairiness")
    private float hairiness = 100f;

    @ColumnInfo(name = "stress")
    private float stress = 100f;

    @ColumnInfo(name = "immunity")
    private float immunity = 100f;

    @ColumnInfo(name = "money")
    private float money = 100f;

    @Ignore
    private ProgressBar hp_bar;

    @Ignore
    private Property[] propertys = new Property[]{
            new Property("hungry"),
            new Property("happy"),
            new Property("intelligence_development"),
            new Property("nerdiness"),
            new Property("tiredness"),
            new Property("hairiness"),
            new Property("stress"),
            new Property("immunity"),
            new Property("money")
    };
    @Ignore
    private static Hero instance;
    // Конструктор для Room
    public Hero() {
        syncPropertiesFromColumns();
    }

    @Ignore
    public Hero(ProgressBar hpBar, ProgressBar[] progressBars) {
        this.hp_bar = hpBar;
        initProgressBars(progressBars);
        syncPropertiesFromColumns();
    }

    private void initProgressBars(ProgressBar[] progressBars) {
        for (int i = 0; i < progressBars.length; i++) {
            if (progressBars[i] != null) {
                propertys[i].setProgressBar(progressBars[i]);
            }
        }
    }

    // Синхронизация значений между колонками и объектами Property
    public void syncPropertiesToColumns() {
        hungry = propertys[0].getValue();
        happy = propertys[1].getValue();
        intelligence = propertys[2].getValue();
        nerdiness = propertys[3].getValue();
        tiredness = propertys[4].getValue();
        hairiness = propertys[5].getValue();
        stress = propertys[6].getValue();
        immunity = propertys[7].getValue();
        money = propertys[8].getValue();
    }

    public void syncPropertiesFromColumns() {
        propertys[0].setValue(hungry);
        propertys[1].setValue(happy);
        propertys[2].setValue(intelligence);
        propertys[3].setValue(nerdiness);
        propertys[4].setValue(tiredness);
        propertys[5].setValue(hairiness);
        propertys[6].setValue(stress);
        propertys[7].setValue(immunity);
        propertys[8].setValue(money);
    }

    // Метод для повторной привязки ProgressBar
    private void reBar(ProgressBar hpBar, ProgressBar[] progressBars) {
        hp_bar = hpBar;
        for (int i = 0; i < progressBars.length; i++) {
            if (progressBars[i] != null) {
                propertys[i].setProgressBar(progressBars[i]);
            }
        }
    }

    // Инициализация синглтона
    public static synchronized Hero initialize(ProgressBar hpBar, ProgressBar[] progressBars) {
        if (instance == null) {
            instance = new Hero(hpBar, progressBars);
        }
        instance.reBar(hpBar, progressBars);
        return instance;
    }

    // Проверка жизней
    public boolean isAlive() {
        return hp > 0;
    }

    // Основная логика обновления
    public void update() {
        for (Property property : propertys) {
            this.hp -= (DELTA_HP_POINT - (property.getValue() / 30));
            if (this.hp > MAX_HP) {
                this.hp = MAX_HP;
            }
            if (property.getValue() > 0) {
                property.setValue(property.getValue() - DELTA_PROPERTY_POINT);
            }
        }
        syncPropertiesToColumns(); // Автосохранение при обновлении
        syncPropertiesFromColumns();
    }

    // Геттеры/сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public float getHp() { return hp; }
    public void setHp(float hp) { this.hp = hp; }

    public float getHungry() { return hungry; }
    public void setHungry(float hungry) { this.hungry = hungry; }

    public float getHappy() { return happy; }
    public void setHappy(float happy) { this.happy = happy; }

    public float getIntelligence() { return intelligence; }
    public void setIntelligence(float intelligence) { this.intelligence = intelligence; }

    public float getNerdiness() { return nerdiness; }
    public void setNerdiness(float nerdiness) { this.nerdiness = nerdiness; }

    public float getTiredness() { return tiredness; }
    public void setTiredness(float tiredness) { this.tiredness = tiredness; }

    public float getHairiness() { return hairiness; }
    public void setHairiness(float hairiness) { this.hairiness = hairiness; }

    public float getStress() { return stress; }
    public void setStress(float stress) { this.stress = stress; }

    public float getImmunity() { return immunity; }
    public void setImmunity(float immunity) { this.immunity = immunity; }

    public float getMoney() { return money; }
    public void setMoney(float money) { this.money = money; }

    public ProgressBar getHp_bar() { return hp_bar; }
    public Property[] getPropertys() { return propertys; }

    // Внутренний класс Property
    class Property {
        private String name;
        private float value = 100f;
        @Ignore
        private ProgressBar progressBar;

        public Property(String name) {
            this.name = name;
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }

        public String getName() {
            return name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }
    public static Hero loadFromDatabase(Application application,
                                        ProgressBar hpBar,
                                        ProgressBar[] progressBars) {
        // Получаем экземпляр БД
        HeroDB db = HeroDB.getInstance(application);

        // Получаем DAO
        HeroDao heroDao = db.heroDao();

        // Загружаем данные из БД
        Hero savedHero = heroDao.getHero();
        Log.d("NEW HERO GENERATED", "START");
        if (savedHero != null) {
            // Обновляем UI-компоненты
            savedHero.reBar(hpBar, progressBars);

            savedHero.syncPropertiesFromColumns();
            Log.d("NEW HERO GENERATED", "OLD");

            return savedHero;
        } else {
            Log.d("NEW HERO GENERATED", "ACCES");
            // Создаем и сохраняем нового героя
            Hero newHero = Hero.initialize(hpBar, progressBars);

            // Синхронизируем данные перед сохранением
            newHero.syncPropertiesToColumns();

            // Сохраняем в БД в фоновом потоке
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                heroDao.insert(newHero);
            });

            return loadFromDatabase(application, hpBar, progressBars);
        }
    }

    // Синглтон-инстанс

}