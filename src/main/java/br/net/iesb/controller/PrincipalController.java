package br.net.iesb.controller;

import br.net.iesb.entity.*;
import br.net.iesb.json.AutomovelFiltroJson;
import br.net.iesb.json.FabricanteFiltroJson;
import br.net.iesb.service.*;
import br.net.iesb.view.AutomovelListaView;
import br.net.iesb.view.AutomovelView;
import br.net.iesb.view.FabricanteListaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

}
