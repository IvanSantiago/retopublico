Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '/mimappir/ext-3.2.1/resources/images/default/s.gif';
	
    var Contact = Ext.data.Record.create([
	{name: 'uid_SI'},
    {
        name: 'categoria',
        type: 'string'
    }, {
        name: 'descripcion',
        type: 'string'
    }, {
        name: 'nombre',
        type: 'string'
    }]);
    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'view.action',
            create : 'create.action',
            update: 'update.action',
            destroy: 'delete.action'
        }
    });
    
    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: 'uid_SI',
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    Contact);

 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'user',
        proxy: proxy,
        reader: reader,
        writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
        autoSave: false // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    });

    //read the data from simple array
    store.load();
    
    Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
    	Ext.Msg.show({
    		title: 'ERROR',
    		msg: res.message,
    		icon: Ext.MessageBox.ERROR,
    		buttons: Ext.Msg.OK
    	});
    });

    
    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });
    

    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {header: "categoria",
             width: 170,
             sortable: true,
             dataIndex: 'categoria',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "DESCRIPCION #",
             width: 160,
             sortable: true,
             dataIndex: 'descripcion',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "nombre",
             width: 170,
             sortable: true,
             dataIndex: 'nombre',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'My Contacts',
        height: 300,
        width:535,
		frame:true,
		tbar: [{
            iconCls: 'icon-user-add',
            text: 'Add Contact',
            handler: function(){
                var e = new Contact({
                    categoria: 'New Guy',
                    descripcion: '(000) 000-0000',
                    nombre: 'new@loianetest.com'
                });
                editor.stopEditing();
                store.insert(0, e);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        },{
            iconCls: 'icon-user-delete',
            text: 'Remove Contact',
            handler: function(){
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                    store.remove(r);
                }
            }
        },{
            iconCls: 'icon-user-save',
            text: 'Save All Modifications',
            handler: function(){
                store.save();
            }
        }]
    });

    //render to DIV
    grid.render('crud-grid');
});