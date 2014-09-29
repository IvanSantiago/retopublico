Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '/mimappir/ext-3.2.1/resources/images/default/s.gif';
	
	var EFromPrincipal 
	, BTNEntrar, BTNCancelar
	, TFcusuario, TFcpassword;
    
	
	/** DEFINICION de objetos dentro de la página y sus componentes 
     * */
    BTNEntrar =  new Ext.Button({
		iconCls: 'icon-login',
        itemId: 'ENTRAR',
        text: 'Entrar',
        hidden:false,
        enableToggle : true,
        //padding:'50 30 10 30',
        //margin:'0 50 0 50',
        handler: function(){
            var form = EFromPrincipal.getForm();
            if (form.isValid()) {
            	//form.submit();
            	
            	form.submit({
            	    clientValidation: true,
            	    url: '../j_spring_security_check',
            	    success: function(form, action) {
            	       //Ext.Msg.alert('Success', 'here');
            	       window.location=action.result.url;
            	    },
            	    failure: function(form, action) {
            	    	Ext.Msg.alert('ERROR', 'El usuario o password ingresados no son correctos');
            	        switch (action.failureType) {
            	            //case Ext.form.action.Action.CLIENT_INVALID:
            	        case action.Action.CLIENT_INVALID:
            	                Ext.Msg.alert('Falla', 'Los valores ingresados no son validos');
            	                break;
            	           // case Ext.form.action.Action.CONNECT_FAILURE:
            	        case action.Action.CONNECT_FAILURE:
            	                Ext.Msg.alert('Falla', 'Comunicación con el servidor a tenido errores');
            	                break;
            	            //case Ext.form.action.Action.SERVER_INVALID:
            	        case action.Action.SERVER_INVALID:
            	               Ext.Msg.alert('Falla', action.result.msg);
            	       }
            	    }
            	});          	
            }
        }
    });
    
    BTNCancelar =  new Ext.Button({
        iconCls: 'icon-close',
        itemId: 'CLOSE',
        text: 'Cancelar',        
        hidden:false,
        bodyStyle:'padding:5px 5px 0',
        enableToggle : true,        
        //padding:'10 30 10 30',
        //margin:'60 60 60 60',
        handler: function(){
            var form = EFromPrincipal.getForm();
            if (form.isValid()) {
            	form.reset();                
            }
        }
    });
        
    TFcusuario = new Ext.form.TextField({
		id : 'j_username',
        itemId: 'j_username',
		name : 'j_username',
		fieldLabel: 'USUARIO ',
		margin:'0 20 0 20',
		anchor:'98%',
		labelAlign : 'left',
        allowBlank: false
	});
    
    TFcpassword = new Ext.form.TextField({
        fieldLabel: 'PASSWORD ',
        name: 'j_password',
        itemId: 'j_password',      
        margin:'0 20 0 20',
        inputType: 'password',
        anchor:'98%',
        labelAlign : 'left',
        allowBlank: false    
	});
	
    
    EFromPrincipal = new Ext.FormPanel({
        labelAlign: 'top',
        frame: true,
        //url: '../j_spring_security_check',
        title: 'Acceso a sistema',
        bodyStyle:'padding:5px 5px 0',
        //width: 700,
        items: [{
            layout:'column',
            items:[{
                columnWidth:.1,
                layout: 'form',
                items: []
            },{
                columnWidth:.45,
                layout: 'form',
                items: [TFcusuario]
            },{
                columnWidth:.45,
                layout: 'form',
                items: [TFcpassword]
            },{
                columnWidth:.1,
                layout: 'form',                
                items: [/*BTNEntrar*/]
            },{
                columnWidth:.1,
                layout: 'form',	
                
                items: [/*BTNCancelar*/]
            },{
                columnWidth:.2,
                layout: 'form',
                items: []
            }]
        }],
        bbar : ['->'
                ,'-',BTNEntrar,'-'
                ,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '
                ,'-',BTNCancelar,'-'
                ,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '
                ,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '
                ,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '
                ]
    });

    EFromPrincipal.render('editableForm');
    
});