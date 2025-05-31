package com.bank.util;

import com.bank.model.Account;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.nio.file.Path;
import java.util.HashMap;

public class AccountLoader {
  private static final Gson GSON = new Gson();

  public static Map<String,Account> loadFromJson(Path jsonFile) {
    try (FileReader reader = new FileReader(jsonFile.toFile())) {
        // First parse into a Map<String,Account> where each Account.acctNum is null:
        Type type = new TypeToken<Map<String,Account>>(){}.getType();
        Map<String,Account> rawMap = GSON.fromJson(reader, type);

        Map<String,Account> result = new HashMap<>();
        for (Map.Entry<String,Account> entry : rawMap.entrySet()) {
            String acctKey = entry.getKey();
            Account a = entry.getValue();
            Account fixed = new Account(
                acctKey,
                a.getName(),
                a.getPin(),
                a.getBalance()
            );
            result.put(acctKey, fixed);
        }
        return result;
    } catch (IOException e) {
        throw new RuntimeException("Failed to load JSON: " + e.getMessage(), e);
    }
}
  public static void saveToJson(Path jsonFile, Map<String,Account> db) {
    try (FileWriter w = new FileWriter(jsonFile.toFile())) {
        GSON.toJson(db, w);
    }
    catch (IOException e) {
        throw new RuntimeException("Failed to save JSON: " + e.getMessage(), e);
    }
  }
}
