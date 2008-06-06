#ifndef BUILTINS_H
#define BUILTINS_H
#include <MEPT.h>

/* DO NOT EDIT. This file is generated */

void initBuiltins(void);
PT_Tree forwardBuiltin(ATerm builtin, PT_Tree input);
PT_Tree ASFE_set_anno(PT_Symbol type , PT_Tree arg0, PT_Tree arg1, PT_Tree arg2);
PT_Tree ASC_set_anno(ATerm type , ATerm arg0, ATerm arg1, ATerm arg2);

PT_Tree ASFE_get_anno(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_get_anno(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_get_term_anno(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_get_term_anno(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_get_lex_term_anno(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_get_lex_term_anno(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_set_term_anno(PT_Symbol type , PT_Tree arg0, PT_Tree arg1, PT_Tree arg2);
PT_Tree ASC_set_term_anno(ATerm type , ATerm arg0, ATerm arg1, ATerm arg2);

PT_Tree ASFE_execute_command(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_execute_command(ATerm type , ATerm arg0);

PT_Tree ASFE_read_from_command(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_read_from_command(ATerm type , ATerm arg0);

PT_Tree ASFE_term_less(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_term_less(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_lift_to_tree(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_lift_to_tree(ATerm type , ATerm arg0);

PT_Tree ASFE_lower_from_tree(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_lower_from_tree(ATerm type , ATerm arg0);

PT_Tree ASFE_lift_to_term(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_lift_to_term(ATerm type , ATerm arg0);

PT_Tree ASFE_implode(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_implode(ATerm type , ATerm arg0);

PT_Tree ASFE_parse_file(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_parse_file(ATerm type , ATerm arg0);

PT_Tree ASFE_parse_file_pos_info(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_parse_file_pos_info(ATerm type , ATerm arg0);

PT_Tree ASFE_parse_bytes(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_parse_bytes(ATerm type , ATerm arg0);

PT_Tree ASFE_parse_bytes_pos_info(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_parse_bytes_pos_info(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_unparse_to_bytes(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_unparse_to_bytes(ATerm type , ATerm arg0);

PT_Tree ASFE_unparse_to_file(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_unparse_to_file(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_read_term_from_file(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_read_term_from_file(ATerm type , ATerm arg0);

PT_Tree ASFE_write_term_to_file(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_write_term_to_file(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_read_bytes_from_file(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_read_bytes_from_file(ATerm type , ATerm arg0);

PT_Tree ASFE_write_bytes_to_file(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_write_bytes_to_file(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_tide_connect(PT_Symbol type , PT_Tree arg0, PT_Tree arg1);
PT_Tree ASC_tide_connect(ATerm type , ATerm arg0, ATerm arg1);

PT_Tree ASFE_tide_disconnect(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_tide_disconnect(ATerm type , ATerm arg0);

PT_Tree ASFE_tide_step(PT_Symbol type , PT_Tree arg0);
PT_Tree ASC_tide_step(ATerm type , ATerm arg0);

PT_Tree ASFE_next_id(PT_Symbol type );
PT_Tree ASC_next_id(ATerm type );

#endif
