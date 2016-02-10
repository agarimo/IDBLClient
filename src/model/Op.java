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
import util.Sql;
import util.Varios;

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
            return "INSERT INTO idbl_stats.exception (host_ip,host_name,user,os,adminMode,exception) values("
                    + Varios.entrecomillar(Var.host.getIp()) + ","
                    + Varios.entrecomillar(Var.host.getHostname()) + ","
                    + Varios.entrecomillar(Var.host.getUser()) + ","
                    + Varios.entrecomillar(Var.host.getOs()) + ","
                    + Var.modoAdmin + ","
                    + Varios.entrecomillar(this.opDetail)
                    + ");";
        } else {
            return "INSERT INTO idbl_stats.op (host_ip,host_name,user,os,adminMode,op_type,op_detail) values("
                    + Varios.entrecomillar(Var.host.getIp()) + ","
                    + Varios.entrecomillar(Var.host.getHostname()) + ","
                    + Varios.entrecomillar(Var.host.getUser()) + ","
                    + Varios.entrecomillar(Var.host.getOs()) + ","
                    + Var.modoAdmin + ","
                    + Varios.entrecomillar(this.opType.toString()) + ","
                    + Varios.entrecomillar(this.opDetail)
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
        thread.start();
    }

}
