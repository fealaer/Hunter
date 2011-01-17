/*
 * ServersJPanel.java
 *
 * Created on 17.01.2011, 10:30:21
 */
package hunter;

import hunter.HunterServerInterface;
import java.util.Enumeration;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class GamesJPanel extends javax.swing.JPanel {

    /** Creates new form ServersJPanel */
    public GamesJPanel() {
        initComponents();
        setGamesList();
    }

    @Override
    public void setVisible(boolean aFlag) {
        setGamesList();
        super.setVisible(aFlag);
    }

    private void setGamesList()
    {
        HunterServerInterface server = Main.getServer();
        Enumeration list = server.getGamesList();
        while(list.hasMoreElements())
        {
            gamesList.addItem(list.nextElement());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gamesList = new javax.swing.JComboBox();
        newGame = new javax.swing.JLabel();
        games = new javax.swing.JLabel();
        addServer = new javax.swing.JButton();
        toGames = new javax.swing.JButton();
        gameName = new javax.swing.JTextField();
        toPlayers = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        refreshList = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 300));

        gamesList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gamesListActionPerformed(evt);
            }
        });

        newGame.setText("New Game:");

        games.setText("Games:");

        addServer.setText("Add");
        addServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServerActionPerformed(evt);
            }
        });

        toGames.setText("Next >>");
        toGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toGamesActionPerformed(evt);
            }
        });

        gameName.setText("Game Name");
        gameName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameNameActionPerformed(evt);
            }
        });

        toPlayers.setText("<< Prev");
        toPlayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toPlayersActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose Game");

        refreshList.setText("Refresh");
        refreshList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newGame, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(games, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gameName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(gamesList, 0, 202, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshList))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(addServer, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(toPlayers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(toGames)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gamesList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(games)
                    .addComponent(refreshList))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gameName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newGame))
                .addGap(16, 16, 16)
                .addComponent(addServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toGames)
                    .addComponent(toPlayers))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServerActionPerformed
        String newServerIpPort = gameName.getText();
        if (newServerIpPort == null
                || newServerIpPort.equalsIgnoreCase("IP[:port]")
                || newServerIpPort.equalsIgnoreCase("")) {
            return;

        }
        gamesList.addItem(newServerIpPort);
        gamesList.setSelectedItem(newServerIpPort);
}//GEN-LAST:event_addServerActionPerformed

    private void toGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toGamesActionPerformed
        GameFrame frame = (GameFrame) Main.getFrame();
        frame.changePanelsActivity(this, "GameJPanel");
}//GEN-LAST:event_toGamesActionPerformed

    private void toPlayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toPlayersActionPerformed
        GameFrame frame = (GameFrame) Main.getFrame();
        frame.changePanelsActivity(this, "ServersJPanel");
}//GEN-LAST:event_toPlayersActionPerformed

    private void gameNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gameNameActionPerformed

    private void gamesListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gamesListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gamesListActionPerformed

    private void refreshListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshListActionPerformed
        setGamesList();
    }//GEN-LAST:event_refreshListActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addServer;
    private javax.swing.JTextField gameName;
    private javax.swing.JLabel games;
    private javax.swing.JComboBox gamesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel newGame;
    private javax.swing.JButton refreshList;
    private javax.swing.JButton toGames;
    private javax.swing.JButton toPlayers;
    // End of variables declaration//GEN-END:variables
}
