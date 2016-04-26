package com.example;

import java.util.ArrayList;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator implements ServiceHelper{
    private Entity nurse;
    private Entity bed;
    private Entity patient;
    private Entity house;
    private Entity temper;

    private ArrayList<OneClass> ones = new ArrayList<>();
    private ArrayList<OneClass> manys = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "nurse_db");
        // 保证自定义的代码不会被覆盖
        schema.enableKeepSectionsByDefault();
        // 生成相应的ContentProvider支持
        // entity.addContentProvider();
        GreenDaoGenerator generator = new GreenDaoGenerator();
        // 添加所有表
        generator.addTables(schema);
        // 添加表中字段
        generator.addNurseTable(generator.nurse);
        generator.addBedInfoTable(generator.bed);
        generator.addPatientInfoTable(generator.patient);
        generator.addHouseInfoTable(generator.house);
        generator.addTemperInfoTable(generator.temper);
        // 添加外键映射关系
        generator.addToOne();
        generator.addToMany();

        new DaoGenerator().generateAll(schema, "./app/src/main/java-gen");
    }

    // 添加表
    private void addTables(Schema schema) {
        nurse = schema.addEntity(NURSE_INFO_TABLE_NAME);
        bed = schema.addEntity(BED_INFO_TABLE_NAME);
        patient = schema.addEntity(PATIENT_INFO_TABLE_NAME);
        house = schema.addEntity(HOUSE_INFO_TABLE_NAME);
        temper = schema.addEntity(TEMPER_INFO_TABLE_NAME);
    }

    // 统一添加一对一关系
    private void addToOne() {
        for (OneClass one : ones) {
            one.handler.addToOne(one.target, one.property);
        }
        ones.clear();
    }

    // 添加一对多关系
    private void addToMany() {
        for (OneClass many : manys) {
            many.handler.addToMany(many.target, many.property).setName(many.name);
        }
        manys.clear();
    }

    // 外键映射关系
    private static class OneClass {
        Entity handler;
        Entity target;
        Property property;
        String name;

        public OneClass(Entity handler, Entity target, Property property) {
            this.handler = handler;
            this.target = target;
            this.property = property;
        }

        public OneClass(Entity handler, Entity target, Property property, String name) {
            this.handler = handler;
            this.target = target;
            this.property = property;
            this.name = name;
        }
    }

    // 添加Nurse表
    private void addNurseTable(Entity entity) {
        entity.addStringProperty(NURSE_ID).primaryKey().unique().notNull();
        entity.addStringProperty(NURSE_GENDER).notNull();
        entity.addIntProperty(NURSE_AGE).notNull();
        entity.addStringProperty(NURSE_MAJOR).notNull();
        entity.addStringProperty(NURSE_NAME).notNull();
        entity.addStringProperty(NURSE_PHOTO).notNull();
    }

    // 添加Bed_info表
    private void addBedInfoTable(Entity entity) {
        entity.addStringProperty(BED_ID).primaryKey().unique().notNull();
        Property house_id = entity.addStringProperty(HOUSE_ID).notNull().getProperty();
        Property patient_id = entity.addStringProperty(PATIENT_ID).notNull().getProperty();
        entity.addStringProperty(BED_STATE).notNull();

        ones.add(new OneClass(entity, patient, patient_id));
    }

    // 添加patient_info表
    private void addPatientInfoTable(Entity entity) {
        entity.addStringProperty(PATIENT_ID).primaryKey().unique().notNull();
        entity.addStringProperty(TAG_ID).unique().notNull();
        entity.addStringProperty(PATIENT_NAME).notNull();
        entity.addIntProperty(PATIENT_AGE).notNull();
        entity.addStringProperty(PATIENT_GENDER).notNull();
        entity.addStringProperty(PATIENT_RECORD).notNull();
        entity.addStringProperty(PATIENT_PHOTO).notNull();

        Property bed_id = entity.addStringProperty(BED_ID).notNull().getProperty();
        ones.add(new OneClass(entity, bed, bed_id));
    }

    private void addHouseInfoTable(Entity entity) {
        entity.addStringProperty(HOUSE_ID).primaryKey().unique().notNull();
        Property nurse_id = entity.addStringProperty(NURSE_ID).notNull().getProperty();
        entity.addStringProperty(HOUSE_STATE).notNull();

        // 设置一对多关系
        // 一个HOUSE对应多个BED

    }

    private void addTemperInfoTable(Entity entity) {
        entity.addLongProperty(ID).primaryKey().autoincrement().notNull();
        entity.addStringProperty(TAG_ID).notNull();
        entity.addFloatProperty(TEMPER_NUM).notNull();
        Property nurse_id = entity.addStringProperty(NURSE_ID).notNull().getProperty();
        entity.addDateProperty(LAST_TIME).notNull();
        entity.addStringProperty(NEXT_TIME);

        ones.add(new OneClass(entity, nurse, nurse_id));
    }
}
