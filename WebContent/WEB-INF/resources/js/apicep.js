jQuery('#input-numdoc').mask('999.999.999-99');
jQuery('#input-foncli').mask('(99) 99999-9999');
jQuery('#input-foncl2').mask('(99) 99999-9999');
jQuery('#input-datnas').mask('99/99/9999');
jQuery('#input-datemi').mask('99/99/9999');

jQuery('#input-tipcli').change(function() {
    if (jQuery(this).val() === 'F') {
        jQuery('#input-numdoc').val("")
            .mask('999.999.999-99')
            .focus();
    } else {
        jQuery('#input-numdoc').val("")
            .mask('99.999.999/9999-99')
            .focus();
    }
});

jQuery('#input-cepcli').mask('00000-000').change(function() {
    let cep = jQuery(this).val().replace(/\D/g, '');
    if (cep !== '') {
        $.ajax({
            url: 'https://viacep.com.br/ws/' + jQuery(this).val() + '/json/',
            dataType: 'json',
            success: function(result){
                $("#input-endcli").val(result.logradouro);
                $("#input-comend").val(result.complemento);
                $("#input-baicli").val(result.bairro);
                $("#input-muncli").val(result.localidade);
                $("#input-ufscli").val(result.uf);
                $("#input-numend").focus();
            },
            error: () => {

            }
        });
    }
});