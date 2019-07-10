/*
 * The MIT License
 *
 * Copyright 2016 Agarimo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Var;
import sql.Sql;
import tools.Util;

/**
 *
 * @author Agarimo
 */
public class Op {

    private OpType opType;
    private String opDetail;

    public Op() {

    }

    public Op(OpType opType, String opDetail) {
        this.opType = opType;
        this.opDetail = opDetail;
    }

    public void setOpType(OpType opType) {
        this.opType = opType;
    }

    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }

    private String getQuery() {
        if (opType == OpType.EXCEPTION) {
            return "INSERT INTO " + Var.dbNameStats + ".exception (id_host,adminMode,exception) values("
                    + Var.host.getId() + ","
                    + Var.modoAdmin + ","
                    + Util.comillas(this.opDetail)
                    + ");";
        } else {
            return "INSERT INTO " + Var.dbNameStats + ".op (id_host,adminMode,op_type,op_detail) values("
                    + Var.host.getId() + ","
                    + Var.modoAdmin + ","
                    + Util.comillas(this.opType.toString()) + ","
                    + Util.comillas(this.opDetail)
                    + ");";
        }
    }

    public void run() {
        Thread thread = new Thread(() -> {
            try {
                Sql bd = new Sql(Var.con);
                bd.ejecutar(getQuery());
                bd.close();
            } catch (SQLException ex) {
                Logger.getLogger(Op.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (Var.logControl) {
            thread.start();
        }
    }

}
