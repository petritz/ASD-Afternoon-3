package at.tugraz.asdafternoon3.businesslogic;

import at.tugraz.asdafternoon3.data.Flat;
import at.tugraz.asdafternoon3.database.DatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class FlatCreator extends Creator<Flat> {

    public FlatCreator() {
    }
    
    @Override
    public boolean validate(Flat object) {
        if (object.getName().length() == 0) {
            return false;
        }

        if (object.getAddress().length() == 0) {
            return false;
        }

        if (object.getSize() == 0) {
            return false;
        }

        // TODO: Name should not contain any special characters
        String str;
        str = object.getName().toLowerCase();

        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'A' && ch <= 'z') || ch == ' ')) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Flat create(Flat object) throws SQLException {
        Dao<Flat, Integer> dao = DatabaseConnection.getInstance().getFlatDao();
        dao.create(object);
        return object;
    }
}
