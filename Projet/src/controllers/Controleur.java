package controllers;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import model.Machine;

import vue.Vue;

/**
 * 
 * Classe Controleur
 * @author Marianna
 * 
 *
 */
public abstract class Controleur implements ActionListener, KeyListener{
	protected Machine modele;
	protected Vue vue;
	
	public Controleur(Vue v,Machine m){
		this.modele=m;
		this.vue=v;
	}

	
	
	public abstract void initListener();
	public abstract void keyTyped(KeyEvent e);
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e); 
	public abstract void actionPerformed(ActionEvent e);
}
