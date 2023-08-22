import{j as X,c as N,h as Y,v as Z,aF as ee,_ as U,J as b,aG as Q,aa as c,aO as S,G as te,H as ne,r as m,aL as oe,o as ae,I as M,K as T,L as u,d as r,Q as g,M as y,ay as C,aH as w}from"./index.7f3ba645.js";import{a as ie,Q as re}from"./QToolbar.e08e31dd.js";import{Q as le}from"./QSelect.8ec0c3c9.js";import{a as ue,Q as q,c as se,b as I}from"./QTabPanels.8f74b4ac.js";import{Q as de}from"./QSplitter.12830cc5.js";import{Q as ce}from"./QPage.d924841a.js";import{u as pe}from"./use-quasar.c7be712f.js";import{T as fe,S as me,o as ve}from"./index.09194b24.js";import{g as O}from"./graphql.6d0adb5b.js";import"./use-key-composition.450dd12f.js";import"./use-dark.4c8cd3f0.js";import"./use-timeout.2d83833b.js";import"./QResizeObserver.89ca0fad.js";import"./touch.3df10340.js";import"./TouchPan.ea08c096.js";import"./axios.898b20c0.js";const ge=["top","middle","bottom"];var ke=X({name:"QBadge",props:{color:String,textColor:String,floating:Boolean,transparent:Boolean,multiLine:Boolean,outline:Boolean,rounded:Boolean,label:[Number,String],align:{type:String,validator:e=>ge.includes(e)}},setup(e,{slots:t}){const o=N(()=>e.align!==void 0?{verticalAlign:e.align}:null),n=N(()=>{const a=e.outline===!0&&e.color||e.textColor;return`q-badge flex inline items-center no-wrap q-badge--${e.multiLine===!0?"multi":"single"}-line`+(e.outline===!0?" q-badge--outline":e.color!==void 0?` bg-${e.color}`:"")+(a!==void 0?` text-${a}`:"")+(e.floating===!0?" q-badge--floating":"")+(e.rounded===!0?" q-badge--rounded":"")+(e.transparent===!0?" q-badge--transparent":"")});return()=>Y("div",{class:n.value,style:o.value,role:"status","aria-label":e.label},Z(t.default,e.label!==void 0?[e.label]:[]))}});function z(e){for(var t={},o=e.split(" "),n=0;n<o.length;++n)t[o[n]]=!0;return t}var be=z("abstract as assert boolean break byte case catch char class const continue def default do double else enum extends final finally float for goto if implements import in instanceof int interface long native new package private protected public return short static strictfp super switch synchronized threadsafe throw throws trait transient try void volatile while"),he=z("catch class def do else enum finally for if interface switch trait try while"),ye=z("return break continue"),Ce=z("null true false this"),l;function E(e,t){var o=e.next();if(o=='"'||o=="'")return P(o,e,t);if(/[\[\]{}\(\),;\:\.]/.test(o))return l=o,null;if(/\d/.test(o))return e.eatWhile(/[\w\.]/),e.eat(/eE/)&&(e.eat(/\+\-/),e.eatWhile(/\d/)),"number";if(o=="/"){if(e.eat("*"))return t.tokenize.push(R),R(e,t);if(e.eat("/"))return e.skipToEnd(),"comment";if(L(t.lastToken,!1))return P(o,e,t)}if(o=="-"&&e.eat(">"))return l="->",null;if(/[+\-*&%=<>!?|\/~]/.test(o))return e.eatWhile(/[+\-*&%=<>|~]/),"operator";if(e.eatWhile(/[\w\$_]/),o=="@")return e.eatWhile(/[\w\$_\.]/),"meta";if(t.lastToken==".")return"property";if(e.eat(":"))return l="proplabel","property";var n=e.current();return Ce.propertyIsEnumerable(n)?"atom":be.propertyIsEnumerable(n)?(he.propertyIsEnumerable(n)?l="newstatement":ye.propertyIsEnumerable(n)&&(l="standalone"),"keyword"):"variable"}E.isBase=!0;function P(e,t,o){var n=!1;if(e!="/"&&t.eat(e))if(t.eat(e))n=!0;else return"string";function a(s,d){for(var f=!1,i,h=!n;(i=s.next())!=null;){if(i==e&&!f){if(!n)break;if(s.match(e+e)){h=!0;break}}if(e=='"'&&i=="$"&&!f){if(s.eat("{"))return d.tokenize.push(we()),"string";if(s.match(/^\w/,!1))return d.tokenize.push(xe),"string"}f=!f&&i=="\\"}return h&&d.tokenize.pop(),"string"}return o.tokenize.push(a),a(t,o)}function we(){var e=1;function t(o,n){if(o.peek()=="}"){if(e--,e==0)return n.tokenize.pop(),n.tokenize[n.tokenize.length-1](o,n)}else o.peek()=="{"&&e++;return E(o,n)}return t.isBase=!0,t}function xe(e,t){var o=e.match(/^(\.|[\w\$_]+)/);return o?o[0]=="."?null:"variable":(t.tokenize.pop(),t.tokenize[t.tokenize.length-1](e,t))}function R(e,t){for(var o=!1,n;n=e.next();){if(n=="/"&&o){t.tokenize.pop();break}o=n=="*"}return"comment"}function L(e,t){return!e||e=="operator"||e=="->"||/[\.\[\{\(,;:]/.test(e)||e=="newstatement"||e=="keyword"||e=="proplabel"||e=="standalone"&&!t}function W(e,t,o,n,a){this.indented=e,this.column=t,this.type=o,this.align=n,this.prev=a}function _(e,t,o){return e.context=new W(e.indented,t,o,null,e.context)}function k(e){var t=e.context.type;return(t==")"||t=="]"||t=="}")&&(e.indented=e.context.indented),e.context=e.context.prev}const Se={name:"groovy",startState:function(e){return{tokenize:[E],context:new W(-e,0,"top",!1),indented:0,startOfLine:!0,lastToken:null}},token:function(e,t){var o=t.context;if(e.sol()&&(o.align==null&&(o.align=!1),t.indented=e.indentation(),t.startOfLine=!0,o.type=="statement"&&!L(t.lastToken,!0)&&(k(t),o=t.context)),e.eatSpace())return null;l=null;var n=t.tokenize[t.tokenize.length-1](e,t);if(n=="comment")return n;if(o.align==null&&(o.align=!0),(l==";"||l==":")&&o.type=="statement")k(t);else if(l=="->"&&o.type=="statement"&&o.prev.type=="}")k(t),t.context.align=!1;else if(l=="{")_(t,e.column(),"}");else if(l=="[")_(t,e.column(),"]");else if(l=="(")_(t,e.column(),")");else if(l=="}"){for(;o.type=="statement";)o=k(t);for(o.type=="}"&&(o=k(t));o.type=="statement";)o=k(t)}else l==o.type?k(t):(o.type=="}"||o.type=="top"||o.type=="statement"&&l=="newstatement")&&_(t,e.column(),"statement");return t.startOfLine=!1,t.lastToken=l||n,n},indent:function(e,t,o){if(!e.tokenize[e.tokenize.length-1].isBase)return null;var n=t&&t.charAt(0),a=e.context;a.type=="statement"&&!L(e.lastToken,!0)&&(a=a.prev);var s=n==a.type;return a.type=="statement"?a.indented+(n=="{"?0:o.unit):a.align?a.column+(s?0:1):a.indented+(s?0:o.unit)},languageData:{indentOnInput:/^\s*[{}]$/,commentTokens:{line:"//",block:{open:"/*",close:"*/"}},closeBrackets:{brackets:["(","[","{","'",'"',"'''",'"""']}}},Te=[`input  = [ "variable" :"value" ];
res = baw.callService(  "<<host>>",   "<<username>>",   "<<password>>",   "<<app name>>",   "<<service name>>",   input)
  `,'ctx["<<name>>"]',`assert res.status == 200;
json = util.toJSON(res.getBody());
assert json["status"] == "200";
assert  json["data"]["data"]["<<variable>>"]=="value"`,'util.toJSON("{}")'],_e=ee({name:"CodeSnippetComponent",setup(){return{snippets:Te}}});function ze(e,t,o,n,a,s){return b(),Q("ol",null,[c("li",null,[c("a",{href:"#",onClick:t[0]||(t[0]=S(d=>e.$.emit("onLinkClick",e.snippets[0]),["prevent"]))},"Call BAW service")]),c("li",null,[c("a",{href:"#",onClick:t[1]||(t[1]=S(d=>e.$.emit("onLinkClick",e.snippets[1]),["prevent"]))},"Get Input value")]),c("li",null,[c("a",{href:"#",onClick:t[2]||(t[2]=S(d=>e.$.emit("onLinkClick",e.snippets[2]),["prevent"]))},"Test standard response")]),c("li",null,[c("a",{href:"#",onClick:t[3]||(t[3]=S(d=>e.$.emit("onLinkClick",e.snippets[3]),["prevent"]))},"Convert String to JSON")])])}var Be=U(_e,[["render",ze]]);const Ve={components:{Codemirror:fe,CodeSnippetComponent:Be},setup(){const e=pe(),t=[me.define(Se),ve],o=te(),n=ne(),a=m(o.params.name),s=m(),d=oe(),f=m(o.params.id),i=m("snippets"),h=m({}),B=m("no status"),V=m(!1),x=m([{name:"None",id:"-1"}]),D=m(x.value[0]);function j(){n.back()}function J(){V.value=!0}function $(p){d.value=p.view}function G(p){const H=d.value.state.selection.main.head;d.value.dispatch({changes:{from:H,insert:p}})}function K(){i.value="output",B.value="running",O(`{
              evalTestScript(id:"${f.value}",tid:${D.value.id}){
                output
                status
                result
                error
                elapsedTime

              }
      }`,p=>{var v=p.data.evalTestScript;h.value=v,B.value="completed"},p=>{})}function A(){O(`
          {
            getTestScript(id: "${f.value}") {
              id
              lastModified
              code
              name
            }
            allTestData{
              name
              id
            }
          }
        `,p=>{var v=p.data.getTestScript;a.value=v.name,s.value=v.code,x.value.splice(1,x.value.length,...p.data.allTestData)})}function F(){var p=JSON.stringify(s.value);O(`
         mutation{
  updateTestScript(id:"${f.value}",name:"${a.value}",code:${p}){
    id
  }
}
        `,v=>{V.value=!1,e.notify("Saved")},v=>{e.notify("Error while saving")})}return ae(()=>{A()}),{name:a,code:s,saveData:F,goBack:j,handleReady:$,extensions:t,tab:i,evalTestScript:K,testOutput:h,runStatus:B,onCodeChange:J,codeChanged:V,onLinkClick:G,splitterModel:m(65),options:x,selectedTestData:D}}},Oe={class:"q-pa-sm q-gutter-sm"},Qe={key:0,class:"q-pa-sm q-gutter-sm"},Le=c("div",null,"Error Message",-1),Ee={style:{color:"red","font-weight":"bold","background-color":"#ddd","border-color":"1px solid #ccc",padding:"10px","border-radius":"5px","font-family":"'Courier New', Courier, monospace"}},De={key:1,class:"q-pa-sm q-gutter-sm"},Ne=c("div",null,"Output Message",-1),Me={style:{"font-weight":"bold","background-color":"#ddd","border-color":"1px solid #ccc",padding:"10px","border-radius":"5px","font-family":"'Courier New', Courier, monospace"}};function qe(e,t,o,n,a,s){const d=M("codemirror"),f=M("CodeSnippetComponent");return b(),T(ce,null,{default:u(()=>[r(re,{class:"bg-primary text-white"},{default:u(()=>[r(g,{flat:"",round:"",dense:"",icon:"arrow_back",onClick:n.goBack},null,8,["onClick"]),r(ie,null,{default:u(()=>[y(C(n.name),1)]),_:1}),r(le,{borderless:"",modelValue:n.selectedTestData,"onUpdate:modelValue":t[0]||(t[0]=i=>n.selectedTestData=i),options:n.options,"option-value":"id","option-label":"name"},null,8,["modelValue","options"]),r(g,{flat:"",round:"",dense:"",icon:"play_arrow",onClick:n.evalTestScript},null,8,["onClick"]),r(g,{flat:"",round:"",dense:"",icon:"save",onClick:n.saveData},{default:u(()=>[n.codeChanged?(b(),T(ke,{key:0,floating:"",color:"red",rounded:""})):w("",!0)]),_:1},8,["onClick"])]),_:1}),r(de,{modelValue:n.splitterModel,"onUpdate:modelValue":t[4]||(t[4]=i=>n.splitterModel=i),style:{height:"600px"}},{before:u(()=>[r(d,{modelValue:n.code,"onUpdate:modelValue":t[1]||(t[1]=i=>n.code=i),style:{height:"350px"},autofocus:!0,"indent-with-tab":!0,"tab-size":2,extensions:n.extensions,onChange:n.onCodeChange,onReady:n.handleReady},null,8,["modelValue","extensions","onChange","onReady"])]),after:u(()=>[r(ue,{modelValue:n.tab,"onUpdate:modelValue":t[2]||(t[2]=i=>n.tab=i),align:"left"},{default:u(()=>[r(q,{name:"snippets",label:"Snippets"}),r(q,{name:"output",label:"Output"})]),_:1},8,["modelValue"]),r(se,{modelValue:n.tab,"onUpdate:modelValue":t[3]||(t[3]=i=>n.tab=i),animated:""},{default:u(()=>[r(I,{name:"snippets"},{default:u(()=>[r(f,{onOnLinkClick:n.onLinkClick},null,8,["onOnLinkClick"])]),_:1}),r(I,{name:"output"},{default:u(()=>[c("div",Oe,[r(g,{unelevated:"",color:"primary",size:"sm"},{default:u(()=>[y(C(n.runStatus),1)]),_:1}),r(g,{unelevated:"",color:"primary",size:"sm",icon:"timer"},{default:u(()=>[y(C(n.testOutput.elapsedTime)+" ms",1)]),_:1}),n.testOutput.result=="passed"?(b(),T(g,{key:0,outline:"",color:"green",size:"sm"},{default:u(()=>[y("Passed")]),_:1})):w("",!0),n.testOutput.result=="failed"?(b(),T(g,{key:1,outline:"",color:"red",size:"sm"},{default:u(()=>[y("Failed")]),_:1})):w("",!0)]),n.testOutput.error?(b(),Q("div",Qe,[Le,c("pre",Ee,C(n.testOutput.error),1)])):w("",!0),n.testOutput.output?(b(),Q("div",De,[Ne,c("pre",Me,C(n.testOutput.output),1)])):w("",!0)]),_:1})]),_:1},8,["modelValue"])]),_:1},8,["modelValue"])]),_:1})}var et=U(Ve,[["render",qe]]);export{et as default};
