/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

/**
 *
 * @author milos
 */
    public class NavigationItem {
        private String panelName;
        private Object selectedObject;  // Može biti Zaposleni, Posao ili Zadatak

        public NavigationItem(String panelName, Object selectedObject) {
            this.panelName = panelName;
            this.selectedObject = selectedObject;
        }

        public String getPanelName() {
            return panelName;
        }

        public Object getSelectedObject() {
            return selectedObject;
        }
    }
