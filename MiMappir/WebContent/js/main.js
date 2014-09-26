/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */

//
// This is the main layout definition.
//
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
    var tabs = new Ext.TabPanel({
        //renderTo:'tabs',
        resizeTabs:true, // turn on tab resizing
        minTabWidth: 115,
        tabWidth:135,
        enableTabScroll:true,
        width:600,
        height:250,
        defaults: {autoScroll:true},
        plugins: new Ext.ux.TabCloseMenu()
    });
	
   
    function addTAB2(tabPanel, id, title){
        var tab = tabPanel.getComponent(id);
        var url = id;
        if(url.indexOf("logout.action") != -1){//si es para el login
        	window.location = url;
        }else{
            var tabContent = new Ext.Panel({
                id: id,
                title: title,
                layout: 'fit',
                closable: true,
                items: [{
                    xtype: "component",
                    layout: 'fit',
                    autoEl: {
                        tag: "iframe",
                        style: 'height: 100%; width: 100%; frameborder= 0', 
                        src: url
                    } 
                }]
            }).show();
            
            if (!tab) {
                tabPanel.add(tabContent);
            }
            tabPanel.setActiveTab(tabContent);        	
        }
    }
    
	// This is an inner body element within the Details panel created to provide a "slide in" effect
	// on the panel body without affecting the body's box itself.  This element is created on
	// initial use and cached in this var for subsequent access.
	var detailEl;
	
	// This is the main content center region that will contain each example layout panel.
	// It will be implemented as a CardLayout since it will contain multiple panels with
	// only one being visible at any given time.
	var contentPanel = {
		id: 'content-panel',
		region: 'center', // this is what makes this panel into a region within the containing layout
		layout: 'card',
		margins: '2 5 5 2',
		activeItem: 0,
		border: false,
		items:[tabs]
		/*items: [
			// from basic.js:
			start, absolute, accordion, anchor, border, cardTabs, cardWizard, column, fit, form, table, vbox, hbox,
			// from custom.js:
			rowLayout, centerLayout,
			// from combination.js:
			absoluteForm, tabsNestedLayouts
		]*/
	};
	
	
    // Go ahead and create the TreePanel now so that we can use it below
	
	
	
    var treePanel = new Ext.tree.TreePanel({
    	id: 'tree-panel',
    	title: 'MENU',
        region:'north',
        split: true,
        height: 400,
        minSize: 150,
        autoScroll: true,
        
        // tree-specific configs:
        rootVisible: false,
        lines: true,
        singleExpand: true,
        useArrows: true,
        
        loader: new Ext.tree.TreeLoader({
            //dataUrl:'tree-data.json'
            dataUrl:'admsegmenuview.action',
            listeners: {
                "load": {
                	fn: function(){
                		//if(this.root.childNodes.length <= 0){
                		if(treePanel.root.childNodes.length <= 0){
                			Ext.Msg.alert('Aviso', 'No tiene una sesión activa en el sistema favor de entrar nuevamente.');
                			window.location = '../auth/admseglogout.action';
                		}
                	},
                	scope: this 
            	}
           }
            /*callback: function(){
            	alert("lleno");
            	if(treePanel.root.childNodes.length <= 0){
            		window.location = '../auth/logout.action';
            	}
        	} */           	
        }),        
        root: new Ext.tree.AsyncTreeNode()
    });
    //if(treePanel.root.childNodes.length <= 0){window.location = '../auth/logout.action';}
    
	// Assign the changeLayout function to be called on tree node click.
    treePanel.on('click', function(n){
    	var sn = this.selModel.selNode || {}; // selNode is null on initial selection
    	if(n.attributes.leaf && n.id != sn.id){  // ignore clicks on folders and currently selected node 
    		/*Ext.getCmp('content-panel').layout.setActiveItem(n.id + '-panel');*/
    		if(!detailEl){
    			var bd = Ext.getCmp('details-panel').body;
    			bd.update('').setStyle('background','#fff');
    			detailEl = bd.createChild(); //create default empty div
    		}
    		detailEl.hide().update('<p class="details-info">'+n.attributes.detail+'</p>').slideIn('l', {stopFx:true,duration:.2});
    		
    		addTAB2(tabs, n.attributes.url, n.attributes.title);
    		
    		
    	}
    });

    
	// This is the Details panel that contains the description for each example layout.
	var detailsPanel = {
		id: 'details-panel',
        title: 'Detalle',
        region: 'center',
        //bodyStyle: 'padding-bottom:15px;background:#eee;',
		autoScroll: true,
		html: '<p class="details-info">Selecciona una opción del menu para ejecutar alguna accion dentro del sistema.</p>'
    };
	
	
	
	// Finally, build the main layout once all the pieces are ready.  This is also a good
	// example of putting together a full-screen BorderLayout within a Viewport.
    new Ext.Viewport({
		layout: 'border',
		title: 'Sistema Institucional para la Gestión de Tecnologías de la Información y Comunicaciones para usuarios Externos',
        defaults: {
            collapsible: true,
            split: true//,            bodyStyle: 'padding:5px'
        },
		items: [{
			xtype: 'box',
			region: 'north',
			applyTo: 'header',
			height: 30
		},{
			layout: 'border',
	    	id: 'layout-browser',
	        region:'west',
	        border: false,
	        split:true,
			margins: '2 0 5 5',
	        width: 200,
	        minSize: 100,
	        maxSize: 500,
			items: [ treePanel, detailsPanel]
		},
			contentPanel
		],
        renderTo: Ext.getBody()
    });
});